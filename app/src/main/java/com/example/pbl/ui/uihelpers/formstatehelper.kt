package com.example.pbl.ui.uihelpers

// sealed class untuk error
sealed class UiError {
    data class Message(val text: String) : UiError()
    object InvalidCredentials : UiError()
    object NetworkError : UiError()
}

// sign in form state
data class SignInFormState(
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState()
) {
    // form = aggregator, each FieldState owns its own validity
    val isValid: Boolean
        get() = listOf(email, password).all { it.isValid }
}

// sign up form state
data class SignUpFormState(
    val firstName: FieldState = FieldState(),
    val lastName: FieldState = FieldState(),
    val alias: FieldState = FieldState(),
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState()
) {
    val isValid: Boolean
        get() = listOf(
            firstName,
            lastName,
            alias, email,
            password
        ).all { it.isValid }
}