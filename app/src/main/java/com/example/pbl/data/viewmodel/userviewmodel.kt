package com.example.pbl.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pbl.data.model.userData
import com.example.pbl.data.repository.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UserState {
    object Idle : UserState()
    object Loading : UserState()
    data class Success(val user: userData) : UserState()
    data class Error(val message: String) : UserState()
}

sealed class UserActionState {
    object Idle : UserActionState()
    object Loading : UserActionState()
    object Success : UserActionState()
    data class Error(val message: String) : UserActionState()
}

class UserViewModel : ViewModel() {
    private val userRepo = UserRepo()

    private val _userState = MutableStateFlow<UserState>(UserState.Idle)
    val userState: StateFlow<UserState> = _userState

    private val _actionState = MutableStateFlow<UserActionState>(UserActionState.Idle)
    val actionState: StateFlow<UserActionState> = _actionState

    fun getUser(id: String) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            val result = userRepo.getUser(id)
            result.onSuccess { user ->
                _userState.value = UserState.Success(user)
            }.onFailure { exception ->
                _userState.value = UserState.Error(exception.message ?: "Failed to fetch user")
            }
        }
    }

    fun saveUser(user: userData) {
        viewModelScope.launch {
            _actionState.value = UserActionState.Loading
            val result = userRepo.saveUser(user)
            result.onSuccess {
                _actionState.value = UserActionState.Success
                _userState.value = UserState.Success(user)
            }.onFailure { exception ->
                _actionState.value = UserActionState.Error(exception.message ?: "Failed to save user")
            }
        }
    }

    fun resetActionState() {
        _actionState.value = UserActionState.Idle
    }
}

