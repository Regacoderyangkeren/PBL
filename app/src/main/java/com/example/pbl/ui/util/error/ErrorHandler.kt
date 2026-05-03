package com.example.pbl.ui.util.error

import com.example.pbl.ui.form.UiError

// backend error target
sealed class ErrorTarget {
    object EmailField : ErrorTarget()
    object PasswordField : ErrorTarget()
    object Snackbar : ErrorTarget()
    object Inline : ErrorTarget()
}

// backend error yang sudah diproses
data class ResolvedError(
    val message: String,
    val target: ErrorTarget
)

// nge map error dari backend ke ResolvedError yang bisa dipahami UI, termasuk pesan dan targetnya
fun resolveError(error: UiError): ResolvedError = when (error) {
    is UiError.Message -> ResolvedError(
        message = error.text,
        target = routeMessageError(error.text)
    )
    is UiError.InvalidCredentials -> ResolvedError(
        message = com.example.pbl.ui.theme.errorLoginPassword,
        target = ErrorTarget.PasswordField
    )
    is UiError.NetworkError -> ResolvedError(
        message = com.example.pbl.ui.theme.errorNetworkGeneral,
        target = ErrorTarget.Inline
    )
}

// nge route pesan error yang berupa text biasa ke target yang sesuai
private fun routeMessageError(text: String): ErrorTarget = when {
    text.contains("email", ignoreCase = true) -> ErrorTarget.EmailField
    text.contains("user", ignoreCase = true) -> ErrorTarget.EmailField
    else -> ErrorTarget.Snackbar
}