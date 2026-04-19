package com.example.pbl.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.pbl.data.model.Project

class ProjectRepo {
    private val db = FirebaseFirestore.getInstance()

    suspend fun createProject(project: Project): Result<Unit> {
        return try {
            db.collection("projects")
                .document(project.id)
                .set(project)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProject(id: String): Result<Project> {
        return try {
            val doc = db.collection("projects")
                .document(id)
                .get()
                .await()
            Result.success(doc.toObject(Project::class.java)!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProjectsByMember(userId: String): Result<List<Project>> {
        return try {
            val snapshot = db.collection("projects")
                .whereArrayContains("memberIds", userId)
                .get()
                .await()
            Result.success(snapshot.toObjects(Project::class.java))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProjectsByOwner(ownerId: String): Result<List<Project>> {
        return try {
            val snapshot = db.collection("projects")
                .whereEqualTo("ownerId", ownerId)
                .get()
                .await()
            Result.success(snapshot.toObjects(Project::class.java))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProject(id: String, updates: Map<String, Any>): Result<Unit> {
        return try {
            db.collection("projects")
                .document(id)
                .update(updates)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addMember(projectId: String, userId: String): Result<Unit> {
        return try {
            val project = getProject(projectId).getOrThrow()
            val updatedMembers = project.memberIds + userId
            updateProject(projectId, mapOf("memberIds" to updatedMembers))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun removeMember(projectId: String, userId: String): Result<Unit> {
        return try {
            val project = getProject(projectId).getOrThrow()
            val updatedMembers = project.memberIds - userId
            updateProject(projectId, mapOf("memberIds" to updatedMembers))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteProject(id: String): Result<Unit> {
        return try {
            db.collection("projects")
                .document(id)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}