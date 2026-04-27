package com.example.pbl.data.remote.model

data class Task(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val assignedTo: String = "", // ini userId
    val createdBy: String = "",  // ini userId
    val projectId: String = "",
    val status: TaskStatus = TaskStatus.TODO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: Long = 0L // ini timestamp
)

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
    CANCELLED
}

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}