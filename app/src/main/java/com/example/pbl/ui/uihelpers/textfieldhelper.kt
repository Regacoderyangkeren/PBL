package com.example.pbl.ui.uihelpers

import android.util.Patterns
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pbl.ui.components.AppTextField
import com.example.pbl.ui.components.FieldHintRow
import com.example.pbl.ui.theme.*

// sealed class untuk validasi input
sealed class ValidationResult {
    data class Success(val value: String) : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

// structured state for a single input field
// isValid is trustworthy because error is set eagerly via validateAndUpdate()
data class FieldState(
    val text: String = "",
    val error: String? = null,
    val hasBeenFocused: Boolean = false
) {
    val isValid: Boolean get() = text.isNotEmpty() && error == null
}

// enum tipe validasi
enum class ValidationType {
    GENERAL,
    EMAIL,
    PASSWORD,
    ALPHANUMERIC,
    NUMERIC,
    USERNAME
}

// fungsi untuk mengecek apakah email valid
fun isValidEmail(email: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

// fungsi untuk mengecek apakah password valid
fun isValidPassword(password: String): Boolean =
    password.length >= 10 &&
            password.any { it.isDigit() } &&
            password.any { it.isLetter() } &&
            password.any { it.isUpperCase() } &&
            password.any { !it.isLetterOrDigit() }

// fungsi untuk mengecek apakah input valid
fun validateUserInput(
    input: String,
    type: ValidationType = ValidationType.GENERAL,
    maxLength: Int = when (type) {
        ValidationType.EMAIL -> 254
        ValidationType.PASSWORD -> 128
        ValidationType.ALPHANUMERIC -> 50
        ValidationType.NUMERIC -> 20
        ValidationType.USERNAME -> 20
        ValidationType.GENERAL -> 100
    }
    // kalau input kosong, ini errormsg-nya
): ValidationResult {
    if (input.isBlank()) return ValidationResult.Error(errorInputBlank)

    // trim whitespace
    val trimmed = input.trim()
    // kalau input terlalu panjang, ini errormsg-nya
    if (trimmed.length > maxLength) return ValidationResult.Error(errorInputTooLong(maxLength))

    // ini success dan errormsg per-tipe validasi
    return when (type) {
        ValidationType.EMAIL -> {
            if (isValidEmail(trimmed)) ValidationResult.Success(trimmed)
            else ValidationResult.Error(errorSignUpEmail)
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
        ValidationType.USERNAME -> when {
            trimmed.length < 3 -> ValidationResult.Error(errorSignUpUsername1)
            trimmed.length > 20 -> ValidationResult.Error(errorSignUpUsername2)
            trimmed.contains(" ") -> ValidationResult.Error(errorSignUpUsername3)
            !trimmed.all { it.isLetterOrDigit() } -> ValidationResult.Error(errorNotAlphanumeric)
            else -> ValidationResult.Success(trimmed)
        }
        ValidationType.GENERAL -> ValidationResult.Success(trimmed)
    }
}

// panggil dalem onValueChange karena bakal engatur error
fun FieldState.validateAndUpdate(newText: String, type: ValidationType): FieldState {
    if (newText.isEmpty()) {
        return copy(text = newText, error = null)
    }
    val result = validateUserInput(newText, type)
    return copy(
        text = newText,
        error = if (result is ValidationResult.Error) result.message else null
    )
}

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    validationType: ValidationType = ValidationType.GENERAL,
    hintRows: List<Triple<String, String, Boolean>> = emptyList(),
    hasBeenFocused: Boolean = false,
    shouldValidate: Boolean = false,
    onFocusChanged: ((Boolean) -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    focusedContainerColor: Color = MaterialTheme.colorScheme.primary,
    focusedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    androidx.compose.runtime.LaunchedEffect(isFocused) {
        if (isFocused && !hasBeenFocused) {
            onFocusChanged?.invoke(true)
        }
    }

    Column(modifier = modifier) {
        AppTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            interactionSource = interactionSource,
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            singleLine = singleLine,
            visualTransformation = visualTransformation
        )

        if (hintRows.isNotEmpty()) {
            hintRows.forEach { (hint, errorMsg, isPassed) ->
                FieldHintRow(
                    hint = hint,
                    errorMsg = errorMsg,
                    isPassed = isPassed,
                    isFocused = isFocused,
                    hasBeenFocused = hasBeenFocused,
                    shouldValidate = shouldValidate
                )
            }
        } else if (validationType != ValidationType.GENERAL && value.isNotEmpty()) {
            val result = validateUserInput(value, validationType)
            if (result is ValidationResult.Error) {
                Text(
                    text = result.message,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }
        }
    }
}

// wrapper
@Composable
fun TextFieldContentWrapper(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.padding(16.dp)) {
        content()
    }
}