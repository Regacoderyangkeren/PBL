package com.example.pbl.ui.form

import com.example.pbl.ui.util.helper.FieldState
import com.example.pbl.domain.LoginParams
import com.example.pbl.domain.RegisterParams

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
    // setiap FieldState punya validasi sendiri
    val isValid: Boolean
        get() = listOf(email, password).all { it.isValid }
}

// sign up form state
data class SignUpFormState(
    val firstName: FieldState = FieldState(),
    val lastName: FieldState = FieldState(),
    val alias: FieldState = FieldState(),
    val email: FieldState = FieldState(),
    val password: FieldState = FieldState(),
    val confirmPassword: FieldState = FieldState(),
    val agreedToTerms: Boolean = false
) {
    val isValid: Boolean
        get() = listOf(
            firstName,
            lastName,
            alias,
            email,
            password,
            confirmPassword
        ).all { it.isValid } && agreedToTerms
}

fun SignUpFormState.toRegisterParams() = RegisterParams(
    email = email.text,
    password = password.text,
    firstName = firstName.text,
    lastName = lastName.text,
    alias = alias.text
)

fun SignInFormState.toLoginParams() = LoginParams(
    email = email.text,
    password = password.text
)