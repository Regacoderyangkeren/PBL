package com.example.pbl.data.remote.model

data class Project(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val ownerId: String = "",
    val memberIds: List<String> = emptyList(),
    val status: ProjectStatus = ProjectStatus.ACTIVE,
    val createdAt: Long = 0L
)

enum class ProjectStatus {
    ACTIVE,
    COMPLETED,
    ARCHIVED
}