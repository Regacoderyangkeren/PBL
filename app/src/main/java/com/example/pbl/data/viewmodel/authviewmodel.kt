package com.example.pbl.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.example.pbl.data.repository.AuthRepo
import com.example.pbl.ui.uihelpers.UiError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// auth state (stateful UI)
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val error: UiError) : AuthState()
}

// auth event (one-time UI event)
sealed class AuthEvent {
    object NavigateToHome : AuthEvent()
    data class ShowError(val message: String) : AuthEvent()
}

class AuthViewModel : ViewModel() {
    private val authRepo = AuthRepo()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    // replay=0 untuk one-time events
    private val _authEvent = MutableSharedFlow<AuthEvent>(replay = 0)
    val authEvent: SharedFlow<AuthEvent> = _authEvent.asSharedFlow()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(authRepo.getCurrentUser())
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    fun register(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        alias: String
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepo.register(email, password, firstName, lastName, alias)
            result.onSuccess { user ->
                _currentUser.value = user
                _authState.value = AuthState.Success(user)
                _authEvent.emit(AuthEvent.NavigateToHome)
            }.onFailure { exception ->
                val uiError = mapError(exception.message)
                _authState.value = AuthState.Error(uiError)
                if (uiError is UiError.Message) {
                    _authEvent.emit(AuthEvent.ShowError(uiError.text))
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authRepo.login(email, password)
            result.onSuccess { user ->
                _currentUser.value = user
                _authState.value = AuthState.Success(user)
                _authEvent.emit(AuthEvent.NavigateToHome)
            }.onFailure { exception ->
                val uiError = mapError(exception.message)
                _authState.value = AuthState.Error(uiError)
            }
        }
    }

    fun logout() {
        authRepo.logout()
        _currentUser.value = null
        _authState.value = AuthState.Idle
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    // maps raw error string untuk UiError
    private fun mapError(message: String?): UiError = when (message) {
        "USER_NOT_FOUND" -> UiError.InvalidCredentials
        "WRONG_PASSWORD" -> UiError.InvalidCredentials
        "INVALID_EMAIL" -> UiError.Message("Invalid email format")
        "NETWORK_ERROR" -> UiError.NetworkError
        else -> UiError.Message(message ?: "An unknown error occurred")
    }
}