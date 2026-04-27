package com.example.pbl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pbl.data.repository.AuthRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class EmailVerifyEvent {
    object NavigateToHome : EmailVerifyEvent()
    object VerificationTimeout : EmailVerifyEvent()
}

// seberapa lama polling dilakukan (ms)
private const val POLL_INTERVAL_MS = 3_000L // = 30 detik
// seberapa lama cooldown dilakukan (detik)
private const val RESEND_COOLDOWN_SECS = 60
// seberapa lama bakal ke T.O
private const val VERIFY_TIMEOUT_MS = 5 * 60 * 1_000L

class EmailVerifyViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _event = Channel<EmailVerifyEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val _resendCooldown = MutableStateFlow(RESEND_COOLDOWN_SECS)
    val resendCooldown: StateFlow<Int> = _resendCooldown.asStateFlow()

    private val _isResending = MutableStateFlow(false)
    val isResending: StateFlow<Boolean> = _isResending.asStateFlow()

    private var pollJob : Job? = null
    private var cooldownJob : Job? = null
    private var timeoutJob : Job? = null

    init {
        startPolling()
        startCooldown()
        startTimeout()
    }

    // Polling

    private fun startPolling() {
        pollJob?.cancel()
        pollJob = viewModelScope.launch {
            while (true) {
                delay(POLL_INTERVAL_MS)
                authRepository.reloadAndCheckVerification()
                    .onSuccess { verified ->
                        if (verified) {
                            cancelAllJobs()
                            _event.send(EmailVerifyEvent.NavigateToHome)
                            return@launch
                        }
                    }
                // network hiccup (ngedet) — coba lagi selanjutnya
            }
        }
    }

    // T.O (Timeout)

    private fun startTimeout() {
        timeoutJob?.cancel()
        timeoutJob = viewModelScope.launch {
            delay(VERIFY_TIMEOUT_MS)
            cancelAllJobs(skipTimeout = true)
            authRepository.deleteCurrentUser()
            _event.send(EmailVerifyEvent.VerificationTimeout)
        }
    }

    // Resend

    fun resendVerificationEmail() {
        if (_resendCooldown.value > 0 || _isResending.value) return
        viewModelScope.launch {
            _isResending.value = true
            authRepository.resendVerificationEmail()
                .onFailure { /* silent — bisa coba lago setelah cooldown */ }
            _isResending.value = false
            startCooldown()
        }
    }

    // Cooldown

    private fun startCooldown() {
        cooldownJob?.cancel()
        cooldownJob = viewModelScope.launch {
            _resendCooldown.value = RESEND_COOLDOWN_SECS
            while (_resendCooldown.value > 0) {
                delay(1_000)
                _resendCooldown.value -= 1
            }
        }
    }

    // Cancel (back press / wrong email)

    fun cancelAndDeleteAccount() {
        cancelAllJobs()
        viewModelScope.launch {
            authRepository.deleteCurrentUser()
        }
    }

    // Lifecycle

    override fun onCleared() {
        super.onCleared()
        cancelAllJobs()
        _event.close()
    }

    private fun cancelAllJobs(skipTimeout: Boolean = false) {
        pollJob?.cancel()
        cooldownJob?.cancel()
        if (!skipTimeout) timeoutJob?.cancel()
    }
}