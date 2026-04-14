package com.example.pbl.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.pbl.data.model.userData

class userRepo {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveUser(user: userData): Result<Unit> {
        return try {
            db.collection("users")
                .document(user.id)
                .set(user)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(id: String): Result<userData> {
        return try {
            val doc = db.collection("users")
                .document(id)
                .get()
                .await()

            Result.success(doc.toObject(userData::class.java)!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(id: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            db.collection("users")
                .document(id)
                .update(updates)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(id: String): Result<Unit> {
        return try {
            db.collection("users")
                .document(id)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllUsers(): Result<List<userData>> {
        return try {
            val snapshot = db.collection("users")
                .get()
                .await()

            val users = snapshot.toObjects(userData::class.java)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}