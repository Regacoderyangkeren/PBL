package com.example.pbl.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val success: Color,
    val successContainer: Color,
    val onSuccessContainer: Color
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        success = Color.Unspecified,
        successContainer = Color.Unspecified,
        onSuccessContainer = Color.Unspecified
    )
}

// Light 😇 Theme Colourssss
// biru normal
private val lightThemePrimary = Color(0xFF1A237E)
private val lightThemePrimaryContainer = Color(0xFFC5CAE9)
private val lightThemeOnPrimaryContainer = Color(0xFF0D1445)

// ungu imoet
private val lightThemeSecondary = Color(0xFF6A1B9A)
private val lightThemeSecondaryContainer = Color(0xFFE1BEE7)
private val lightThemeOnSecondaryContainer = Color(0xFF2A083D)

// hijau 👍
private val lightThemeTertiary = Color(0xFF2E7D32)
private val lightThemeTertiaryContainer = Color(0xFFC8E6C9)
private val lightThemeOnTertiaryContainer = Color(0xFF102A13)

// putih normal
private val lightThemeBackground = Color(0xFFFFFFFF)
private val lightThemeSurface = Color(0xFFF5F5F5)

// merah merona
private val lightThemeError = Color(0xFFD32F2F)
private val lightThemeErrorContainer = Color(0xFFFFCDD2)
private val lightThemeOnErrorContainer = Color(0xFF7F0000)

// gren for success
private val lightThemeSuccess = Color(0xFF4CAF50)
private val lightThemeSuccessContainer = Color(0xFFC8E6C9)
private val lightThemeOnSuccessContainer = Color(0xFF1B5E20)

// Dark 😈 Theme Colourssss
// gojo amplify technique fr
private val darkThemePrimary = Color(0xFF9FA8DA)
private val darkThemePrimaryContainer = Color(0xFF1A237E)
private val darkThemeOnPrimaryContainer = Color(0xFFE8EAF6)

// iunno 'bout this bro seems femboy-ish
private val darkThemeSecondary = Color(0xFFCE93D8)
private val darkThemeSecondaryContainer = Color(0xFF4A148C)
private val darkThemeOnSecondaryContainer = Color(0xFFF3E5F5)

// i like this one actually
private val darkThemeTertiary = Color(0xFFA5D6A7)
private val darkThemeTertiaryContainer = Color(0xFF1B5E20)
private val darkThemeOnTertiaryContainer = Color(0xFFE8F5E9)

// background
private val darkThemeBackground = Color(0xFF121212)
private val darkThemeSurface = Color(0xFF1E1E1E)

// error if they are not that mad
private val darkThemeError = Color(0xFFEF5350)
private val darkThemeErrorContainer = Color(0xFF7F0000)
private val darkThemeOnErrorContainer = Color(0xFFFFCDD2)

// gren for success but cooler
private val darkThemeSuccess = Color(0xFF81C784)
private val darkThemeSuccessContainer = Color(0xFF1B5E20)
private val darkThemeOnSuccessContainer = Color(0xFFC8E6C9)

// We use 'em light theme coloursssss
val lightColors = lightColorScheme(
    primary = lightThemePrimary,
    primaryContainer = lightThemePrimaryContainer,
    onPrimaryContainer = lightThemeOnPrimaryContainer,
    secondary = lightThemeSecondary,
    secondaryContainer = lightThemeSecondaryContainer,
    onSecondaryContainer = lightThemeOnSecondaryContainer,
    tertiary = lightThemeTertiary,
    tertiaryContainer = lightThemeTertiaryContainer,
    onTertiaryContainer = lightThemeOnTertiaryContainer,
    background = lightThemeBackground,
    surface = lightThemeSurface,
    error = lightThemeError,
    errorContainer = lightThemeErrorContainer,
    onErrorContainer = lightThemeOnErrorContainer
)

val lightCustomColors = CustomColors(
    success = lightThemeSuccess,
    successContainer = lightThemeSuccessContainer,
    onSuccessContainer = lightThemeOnSuccessContainer
)

// repeat
val darkColors = darkColorScheme(
    primary = darkThemePrimary,
    primaryContainer = darkThemePrimaryContainer,
    onPrimaryContainer = darkThemeOnPrimaryContainer,
    secondary = darkThemeSecondary,
    secondaryContainer = darkThemeSecondaryContainer,
    onSecondaryContainer = darkThemeOnSecondaryContainer,
    tertiary = darkThemeTertiary,
    tertiaryContainer = darkThemeTertiaryContainer,
    onTertiaryContainer = darkThemeOnTertiaryContainer,
    background = darkThemeBackground,
    surface = darkThemeSurface,
    error = darkThemeError,
    errorContainer = darkThemeErrorContainer,
    onErrorContainer = darkThemeOnErrorContainer
)

val darkCustomColors = CustomColors(
    success = darkThemeSuccess,
    successContainer = darkThemeSuccessContainer,
    onSuccessContainer = darkThemeOnSuccessContainer
)
