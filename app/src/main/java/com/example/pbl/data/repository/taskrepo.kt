package com.example.pbl.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.pbl.data.model.Task
import com.example.pbl.data.model.TaskStatus

class TaskRepo {
    private val db = FirebaseFirestore.getInstance()

    suspend fun createTask(task: Task): Result<Unit> {
        return try {
            db.collection("tasks")
                .document(task.id)
                .set(task)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTask(id: String): Result<Task> {
        return try {
            val doc = db.collection("tasks")
                .document(id)
                .get()
                .await()
            Result.success(doc.toObject(Task::class.java)!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTasksByProject(projectId: String): Result<List<Task>> {
        return try {
            val snapshot = db.collection("tasks")
                .whereEqualTo("projectId", projectId)
                .get()
                .await()
            Result.success(snapshot.toObjects(Task::class.java))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTasksByAssignee(userId: String): Result<List<Task>> {
        return try {
            val snapshot = db.collection("tasks")
                .whereEqualTo("assignedTo", userId)
                .get()
                .await()
            Result.success(snapshot.toObjects(Task::class.java))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateTask(id: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            db.collection("tasks")
                .document(id)
                .update(updates)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateTaskStatus(id: String, status: TaskStatus): Result<Unit> {
        return updateTask(id, mapOf("status" to status.name))
    }

    suspend fun deleteTask(id: String): Result<Unit> {
        return try {
            db.collection("tasks")
                .document(id)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}