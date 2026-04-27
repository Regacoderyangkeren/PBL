package com.example.pbl.ui.util.helper

import com.example.pbl.ui.theme.*

// Validation Helpers
data class ValidationHint(
    val hintText: String,
    val errorText: String,
    val isPassed: Boolean
)

// Password Rule Helpers
fun getPasswordRules(
    password: String
): List<ValidationHint> {
    return listOf(
        ValidationHint(
            signUpPasswordHint1,
            errorSignUpPassword1,
            password.length >= 10
        ),
        ValidationHint(
            signUpPasswordHint2,
            errorSignUpPassword2,
            password.any { it.isUpperCase() }
        ),
        ValidationHint(
            signUpPasswordHint3,
            errorSignUpPassword3,
            password.any { it.isDigit() }
        ),
        ValidationHint(
            signUpPasswordHint4,
            errorSignUpPassword4,
            password.any { !it.isLetterOrDigit() }
        )
    )
}

// Alias Rule Helpers
fun getAliasError(
    alias: String
): String {
    return when {
        alias.isEmpty() -> errorInputBlank
        alias.length < 3 -> errorSignUpUsername1
        alias.length > 20 -> errorSignUpUsername2
        alias.contains(" ") -> errorSignUpUsername3
        !alias.all { it.isLetterOrDigit() } -> errorNotAlphanumeric
        else -> ""
    }
}

fun getAliasHint(
    alias: String
): List<ValidationHint> {
    val errorMsg = getAliasError(alias)
    val isValid = errorMsg.isEmpty() && alias.isNotEmpty()
    return listOf(ValidationHint(
        signUpUsernameHint,
        errorMsg,
        isValid)
    )
}

// Simple Field Helpers
fun getRequiredFieldHint(
    value: String,
    hint: String,
    error: String
): List<ValidationHint> {
    return listOf(ValidationHint(
        hint,
        error,
        value.isNotEmpty()
    ))
}

fun getEmailHint(
    email: String
): List<ValidationHint> {
    return listOf(ValidationHint(signUpEmailHint,
        errorSignUpEmail,
        isValidEmail(email)
    ))
}

// confirm password hint
fun getConfirmPasswordHint(
    password: String,
    confirmPassword: String
): List<ValidationHint> {
    return listOf(ValidationHint(
        signUpConfirmPasswordHint,
        errorConfirmPasswordMismatch,
        confirmPassword.isNotEmpty() && confirmPassword == password
    ))
}