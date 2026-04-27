package com.example.pbl.data.repository

import android.util.Log
import com.example.pbl.data.remote.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import com.example.pbl.ui.theme.*

private const val TIMEOUT_MS = 15_000L

class AuthRepository(
    private val userRepository: UserRepository = UserRepository()
) {
    private val auth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
    fun isLoggedIn(): Boolean = auth.currentUser != null

    // Register

    suspend fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        alias: String
    ): Result<FirebaseUser> {
        return try {
            val result = withTimeout(TIMEOUT_MS) {
                auth.createUserWithEmailAndPassword(email, password).await()
            }
            val user = result.user!!

            userRepository.saveUser(
                UserData(
                    id = user.uid,
                    firstName = firstName,
                    lastName = lastName,
                    alias = alias,
                    email = email
                )
            )
            try {
                user.sendEmailVerification().await()
            } catch (e: Exception) {}
            Result.success(user)

        } catch (e: FirebaseAuthUserCollisionException) {
            Result.failure(Exception(errorCodeEmailInUse))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception(errorCodeInvalidEmail))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Login
    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = withTimeout(TIMEOUT_MS) {
                auth.signInWithEmailAndPassword(email, password).await()
            }
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

    fun logout() = auth.signOut()

    // Email verification
    suspend fun reloadAndCheckVerification(): Result<Boolean> = runCatching {
        val user = auth.currentUser ?: error("No signed-in user")
        user.reload().await()
        user.isEmailVerified
    }

    suspend fun resendVerificationEmail(): Result<Unit> = runCatching {
        val user = auth.currentUser ?: error("No signed-in user")
        user.sendEmailVerification().await()
    }

    // Akun
    suspend fun resetPassword(email: String): Result<Unit> = runCatching {
        auth.sendPasswordResetEmail(email).await()
    }

    suspend fun changePassword(newPassword: String): Result<Unit> = runCatching {
        auth.currentUser?.updatePassword(newPassword)?.await()
            ?: error("No signed-in user")
    }

    suspend fun updateEmail(newEmail: String): Result<Unit> = runCatching {
        auth.currentUser?.updateEmail(newEmail)?.await()
            ?: error("No signed-in user")
    }

    suspend fun deleteCurrentUser(): Result<Unit> {
        val user = auth.currentUser ?: return Result.success(Unit)
        return try {
            userRepository.deleteUser(user.uid)
            user.delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}