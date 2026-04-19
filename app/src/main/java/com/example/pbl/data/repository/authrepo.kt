package com.example.pbl.data.repository

import com.example.pbl.data.model.userData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepo {
    private val auth = FirebaseAuth.getInstance()

    // REGISTER
    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        alias: String
    ): Result<FirebaseUser> {
        return try {
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = result.user!!
            
            // Save additional user data
            val repo = UserRepo()
            repo.saveUser(userData(
                id = user.uid,
                firstName = firstName,
                lastName = lastName,
                alias = alias,
                email = email
            ))

            // Send email verification
            user.sendEmailVerification().await()

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // LOGIN
    suspend fun login(
        email: String,
        password: String
    ): Result<FirebaseUser> {
        return try {
            val result = auth
                .signInWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user!!)
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("USER_NOT_FOUND"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception("WRONG_PASSWORD"))
        } catch (e: FirebaseAuthException) {
            Result.failure(Exception(e.errorCode))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // LOGOUT
    fun logout() {
        auth.signOut()
    }

    // CURRENT USER
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    // RESET PASSWORD
    suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // CHANGE PASSWORD
    suspend fun changePassword(newPassword: String): Result<Unit> {
        return try {
            auth.currentUser?.updatePassword(newPassword)?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // UPDATE EMAIL
    suspend fun updateEmail(newEmail: String): Result<Unit> {
        return try {
            auth.currentUser?.updateEmail(newEmail)?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // IS USER LOGGED IN
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}