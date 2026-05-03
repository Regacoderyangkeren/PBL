package com.example.pbl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.example.pbl.domain.LoginParams
import com.example.pbl.domain.RegisterParams
import com.example.pbl.data.repository.AuthRepository
import com.example.pbl.ui.form.UiError
import com.example.pbl.ui.theme.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.TimeoutCancellationException

// State (persistent)
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Error(val error: UiError) : AuthState()
}

// Events
sealed class AuthEvent {
    object NavigateToHome : AuthEvent()
    object NavigateToEmailVerify : AuthEvent()
    object LoadingTimedOut : AuthEvent()
    data class ShowError(val error: UiError) : AuthEvent()
}

// ViewModel
class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _authEvent = Channel<AuthEvent>(Channel.BUFFERED)
    val authEvent = _authEvent.receiveAsFlow()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(authRepository.getCurrentUser())
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    // Register
    fun register(params: RegisterParams) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authRepository.register(
                email = params.email,
                password = params.password,
                firstName = params.firstName,
                lastName = params.lastName,
                alias = params.alias
            ).fold(
                onSuccess = { user ->
                    _currentUser.value = user
                    _authState.value = AuthState.Idle
                    _authEvent.send(AuthEvent.NavigateToEmailVerify)
                },
                onFailure = { e ->
                    handleError(e.message)
                }
            )
        }
    }

    // Login
    fun login(params: LoginParams) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            authRepository.login(
                email = params.email,
                password = params.password
            ).fold(
                onSuccess = { user ->
                    _currentUser.value = user
                    _authState.value = AuthState.Idle
                    val event = if (user.isEmailVerified)
                        AuthEvent.NavigateToHome
                    else
                        AuthEvent.NavigateToEmailVerify
                    _authEvent.send(event)
                },
                onFailure = { e ->
                    handleError(e.message)
                }
            )
        }
    }

    // Session

    fun logout() {
        authRepository.logout()
        _currentUser.value = null
        _authState.value = AuthState.Idle
    }

    fun resetPassword(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            authRepository.resetPassword(email).fold(
                onSuccess = { onSuccess() },
                onFailure = { e -> onFailure(e.message ?: errorCodeNetworkError) }
            )
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }

    // Internal
    private suspend fun handleError(message: String?) {
        // Check if error is a timeout
        if (message?.contains("timeout", ignoreCase = true) == true ||
            message?.contains("timed", ignoreCase = true) == true) {
            _authState.value = AuthState.Error(UiError.Message(errorLoadingTimeout))
            _authEvent.send(AuthEvent.LoadingTimedOut)
        } else {
            val uiError = mapError(message)
            _authState.value = AuthState.Error(uiError)
            _authEvent.send(AuthEvent.ShowError(uiError))
        }
    }

    private fun mapError(message: String?): UiError = when (message) {
        "EMAIL_IN_USE" -> UiError.Message(errorEmailInUse)
        "INVALID_EMAIL" -> UiError.Message(errorSignUpEmail)
        "USER_NOT_FOUND" -> UiError.Message(errorLoginEmail)
        "WRONG_PASSWORD" -> UiError.InvalidCredentials
        "NETWORK_ERROR" -> UiError.NetworkError
        errorCodeInvalidCredential -> UiError.Message(errorLoginInvalidCredential)
        else -> UiError.Message(errorUnknown)
    }

    override fun onCleared() {
        super.onCleared()
        _authEvent.close()
    }
}