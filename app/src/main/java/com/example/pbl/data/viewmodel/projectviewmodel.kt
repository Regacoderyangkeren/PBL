package com.example.pbl.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pbl.data.model.Project
import com.example.pbl.data.repository.ProjectRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProjectListState {
    object Idle : ProjectListState()
    object Loading : ProjectListState()
    data class Success(val projects: List<Project>) : ProjectListState()
    data class Error(val message: String) : ProjectListState()
}

sealed class ProjectDetailState {
    object Idle : ProjectDetailState()
    object Loading : ProjectDetailState()
    data class Success(val project: Project) : ProjectDetailState()
    data class Error(val message: String) : ProjectDetailState()
}

sealed class ProjectActionState {
    object Idle : ProjectActionState()
    object Loading : ProjectActionState()
    object Success : ProjectActionState()
    data class Error(val message: String) : ProjectActionState()
}

class ProjectViewModel : ViewModel() {
    private val projectRepo = ProjectRepo()

    private val _projectListState = MutableStateFlow<ProjectListState>(ProjectListState.Idle)
    val projectListState: StateFlow<ProjectListState> = _projectListState

    private val _projectDetailState = MutableStateFlow<ProjectDetailState>(ProjectDetailState.Idle)
    val projectDetailState: StateFlow<ProjectDetailState> = _projectDetailState

    private val _actionState = MutableStateFlow<ProjectActionState>(ProjectActionState.Idle)
    val actionState: StateFlow<ProjectActionState> = _actionState

    fun getProjectsByMember(userId: String) {
        viewModelScope.launch {
            _projectListState.value = ProjectListState.Loading
            val result = projectRepo.getProjectsByMember(userId)
            result.onSuccess { projects ->
                _projectListState.value = ProjectListState.Success(projects)
            }.onFailure { exception ->
                _projectListState.value = ProjectListState.Error(exception.message ?: "Failed to fetch projects")
            }
        }
    }

    fun getProjectsByOwner(ownerId: String) {
        viewModelScope.launch {
            _projectListState.value = ProjectListState.Loading
            val result = projectRepo.getProjectsByOwner(ownerId)
            result.onSuccess { projects ->
                _projectListState.value = ProjectListState.Success(projects)
            }.onFailure { exception ->
                _projectListState.value = ProjectListState.Error(exception.message ?: "Failed to fetch projects")
            }
        }
    }

    fun getProject(id: String) {
        viewModelScope.launch {
            _projectDetailState.value = ProjectDetailState.Loading
            val result = projectRepo.getProject(id)
            result.onSuccess { project ->
                _projectDetailState.value = ProjectDetailState.Success(project)
            }.onFailure { exception ->
                _projectDetailState.value = ProjectDetailState.Error(exception.message ?: "Failed to fetch project")
            }
        }
    }

    fun createProject(project: Project) {
        viewModelScope.launch {
            _actionState.value = ProjectActionState.Loading
            val result = projectRepo.createProject(project)
            result.onSuccess {
                _actionState.value = ProjectActionState.Success
            }.onFailure { exception ->
                _actionState.value = ProjectActionState.Error(exception.message ?: "Failed to create project")
            }
        }
    }

    fun addMember(projectId: String, userId: String) {
        viewModelScope.launch {
            _actionState.value = ProjectActionState.Loading
            val result = projectRepo.addMember(projectId, userId)
            result.onSuccess {
                _actionState.value = ProjectActionState.Success
            }.onFailure { exception ->
                _actionState.value = ProjectActionState.Error(exception.message ?: "Failed to add member")
            }
        }
    }

    fun removeMember(projectId: String, userId: String) {
        viewModelScope.launch {
            _actionState.value = ProjectActionState.Loading
            val result = projectRepo.removeMember(projectId, userId)
            result.onSuccess {
                _actionState.value = ProjectActionState.Success
            }.onFailure { exception ->
                _actionState.value = ProjectActionState.Error(exception.message ?: "Failed to remove member")
            }
        }
    }

    fun deleteProject(id: String) {
        viewModelScope.launch {
            _actionState.value = ProjectActionState.Loading
            val result = projectRepo.deleteProject(id)
            result.onSuccess {
                _actionState.value = ProjectActionState.Success
            }.onFailure { exception ->
                _actionState.value = ProjectActionState.Error(exception.message ?: "Failed to delete project")
            }
        }
    }

    fun resetActionState() {
        _actionState.value = ProjectActionState.Idle
    }
}