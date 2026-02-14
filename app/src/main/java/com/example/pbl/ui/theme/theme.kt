package com.example.pbl.ui.theme

// compose!
import androidx.compose.runtime.Composable

// if hand phone at dark mode
import androidx.compose.foundation.isSystemInDarkTheme

// material 3 like usual
import androidx.compose.material3.MaterialTheme

@Composable
fun PBLTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
