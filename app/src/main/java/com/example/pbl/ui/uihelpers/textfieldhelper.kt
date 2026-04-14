package com.example.pbl.ui.uihelpers

import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pbl.ui.theme.*


// sealed class untuk validasi input
sealed class ValidationResult {
    data class Success(val value: String) : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

// enum tipe validasi
enum class ValidationType {
    GENERAL,
    EMAIL,
    PASSWORD,
    ALPHANUMERIC,
    NUMERIC
}

// fungsi untuk mengecek apakah email valid
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// fungsi untuk mengecek apakah password valid
fun isValidPassword(password: String): Boolean {
    return password.length >= 10 &&
            password.any { it.isDigit() } &&
            password.any { it.isLetter() } &&
            password.any { it.isUpperCase() } &&
            password.any { !it.isLetterOrDigit() }
}

// main func
fun validateUserInput(
    input: String,
    type: ValidationType = ValidationType.GENERAL,
    maxLength: Int
): ValidationResult {
    if (input.isBlank()) {
        return ValidationResult.Error(errorInputBlank)
    }

    // trim input (baru pertama kali pakai euy)
    val trimmed = input.trim()

    if (trimmed.length > maxLength) {
        return ValidationResult.Error(errorInputTooLong(maxLength))
    }
    
    return when (type) {
        ValidationType.EMAIL -> {
            if (isValidEmail(trimmed)) ValidationResult.Success(trimmed)
            else ValidationResult.Error(errorInvalidEmail)
        }
        ValidationType.PASSWORD -> {
            if (isValidPassword(trimmed)) ValidationResult.Success(trimmed)
            else ValidationResult.Error(errorInvalidPassword)
        }
        ValidationType.ALPHANUMERIC -> {
            if (trimmed.all { it.isLetterOrDigit() || it == ' ' }) ValidationResult.Success(trimmed)
            else ValidationResult.Error(errorNotAlphanumeric)
        }
        ValidationType.NUMERIC -> {
            if (trimmed.all { it.isDigit() }) ValidationResult.Success(trimmed)
            else ValidationResult.Error(errorNotNumeric)
        }
        ValidationType.GENERAL -> {
            ValidationResult.Success(trimmed)
        }
    }
}

// Wrapper
@Composable
fun TextFieldContentWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.padding(16.dp)) {
        content()
    }
}
