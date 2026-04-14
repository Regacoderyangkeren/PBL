package com.example.pbl.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pbl.data.model.Task
import com.example.pbl.data.model.TaskStatus
import com.example.pbl.data.repository.taskRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TaskListState {
    object Idle : TaskListState()
    object Loading : TaskListState()
    data class Success(val tasks: List<Task>) : TaskListState()
    data class Error(val message: String) : TaskListState()
}

sealed class TaskDetailState {
    object Idle : TaskDetailState()
    object Loading : TaskDetailState()
    data class Success(val task: Task) : TaskDetailState()
    data class Error(val message: String) : TaskDetailState()
}

sealed class TaskActionState {
    object Idle : TaskActionState()
    object Loading : TaskActionState()
    object Success : TaskActionState()
    data class Error(val message: String) : TaskActionState()
}

class TaskViewModel : ViewModel() {
    private val taskRepo = taskRepo()

    private val _taskListState = MutableStateFlow<TaskListState>(TaskListState.Idle)
    val taskListState: StateFlow<TaskListState> = _taskListState

    private val _taskDetailState = MutableStateFlow<TaskDetailState>(TaskDetailState.Idle)
    val taskDetailState: StateFlow<TaskDetailState> = _taskDetailState

    private val _actionState = MutableStateFlow<TaskActionState>(TaskActionState.Idle)
    val actionState: StateFlow<TaskActionState> = _actionState

    fun getTasksByProject(projectId: String) {
        viewModelScope.launch {
            _taskListState.value = TaskListState.Loading
            val result = taskRepo.getTasksByProject(projectId)
            result.onSuccess { tasks ->
                _taskListState.value = TaskListState.Success(tasks)
            }.onFailure { exception ->
                _taskListState.value = TaskListState.Error(exception.message ?: "Failed to fetch tasks")
            }
        }
    }

    fun getTasksByAssignee(userId: String) {
        viewModelScope.launch {
            _taskListState.value = TaskListState.Loading
            val result = taskRepo.getTasksByAssignee(userId)
            result.onSuccess { tasks ->
                _taskListState.value = TaskListState.Success(tasks)
            }.onFailure { exception ->
                _taskListState.value = TaskListState.Error(exception.message ?: "Failed to fetch tasks")
            }
        }
    }

    fun getTask(id: String) {
        viewModelScope.launch {
            _taskDetailState.value = TaskDetailState.Loading
            val result = taskRepo.getTask(id)
            result.onSuccess { task ->
                _taskDetailState.value = TaskDetailState.Success(task)
            }.onFailure { exception ->
                _taskDetailState.value = TaskDetailState.Error(exception.message ?: "Failed to fetch task")
            }
        }
    }

    fun createTask(task: Task) {
        viewModelScope.launch {
            _actionState.value = TaskActionState.Loading
            val result = taskRepo.createTask(task)
            result.onSuccess {
                _actionState.value = TaskActionState.Success
            }.onFailure { exception ->
                _actionState.value = TaskActionState.Error(exception.message ?: "Failed to create task")
            }
        }
    }

    fun updateTaskStatus(id: String, status: TaskStatus) {
        viewModelScope.launch {
            _actionState.value = TaskActionState.Loading
            val result = taskRepo.updateTaskStatus(id, status)
            result.onSuccess {
                _actionState.value = TaskActionState.Success
            }.onFailure { exception ->
                _actionState.value = TaskActionState.Error(exception.message ?: "Failed to update task")
            }
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch {
            _actionState.value = TaskActionState.Loading
            val result = taskRepo.deleteTask(id)
            result.onSuccess {
                _actionState.value = TaskActionState.Success
            }.onFailure { exception ->
                _actionState.value = TaskActionState.Error(exception.message ?: "Failed to delete task")
            }
        }
    }

    fun resetActionState() {
        _actionState.value = TaskActionState.Idle
    }
}