package com.example.pbl.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.pbl.data.remote.model.UserData

class UserRepository {
    private val db = FirebaseFirestore.getInstance("pbldatabase")
    private val collection = db.collection("users")
    suspend fun saveUser(user: UserData): Result<Unit> = runCatching {
        collection.document(user.id)
            .set(user)
            .await()
    }

    suspend fun getUser(id: String): Result<UserData> = runCatching {
        collection.document(id)
            .get()
            .await()
            .toObject(UserData::class.java)
            ?: error("User not found")
    }

    suspend fun updateUser(id: String, updates: Map<String, Any>): Result<Unit> = runCatching {
        collection.document(id)
            .update(updates)
            .await()
    }

    suspend fun deleteUser(id: String): Result<Unit> = runCatching {
        collection.document(id)
            .delete()
            .await()
    }

    suspend fun getAllUsers(): Result<List<UserData>> = runCatching {
        collection.get()
            .await()
            .toObjects(UserData::class.java)
    }
}