package com.example.pbl.ui.theme

// Light and Dark More
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Material 3 Colourrrrrs
import androidx.compose.ui.graphics.Color

// Compose!
import androidx.compose.runtime.Composable

// Light :angel: Theme Colourssss
val lightThemePrimary = Color(0xFF001881)
val lightThemePrimaryContainer = Color(0xFFB3BAD9)
val lightThemeOnPrimaryContainer = Color(0xFF000A34)
val lightThemeSecondary = Color(0xFF81007F)
val lightThemeSecondaryContainer = Color(0xFFFFD5FF)
val lightThemeOnSecondaryContainer = Color(0xFF3F003F)
val lightThemeTertiary = Color(0xFF4C8100)
val lightThemeTertiaryContainer = Color(0xFFD0FFB3)
val lightThemeOnTertiaryContainer = Color(0xFF122100)
val lightThemeBackground = Color(0xFFFFFFFF)
val lightThemeSurface = Color(0xFFB4B4B4)
val lightThemeError = Color(0xFFD32F2F)
val lightThemeErrorContainer = Color(0xFFFFCDD2)
val lightThemeOnErrorContainer = Color(0xFFB71C1C)

// Dark :demon: Theme Colourssss
val darkThemePrimary = Color(0xFFFFEB3B)
val darkThemePrimaryContainer = Color(0xFFFFFAC4)
val darkThemeOnPrimaryContainer = Color(0xFF3B4500)
val darkThemeSecondary = Color(0xFF3BFF4E)
val darkThemeSecondaryContainer = Color(0xFFCCFFE4)
val darkThemeOnSecondaryContainer = Color(0xFF00210F)
val darkThemeTertiary = Color(0xFF9D3BFF)
val darkThemeTertiaryContainer = Color(0xFFE5CCFF)
val darkThemeOnTertiaryContainer = Color(0xFF29005A)
val darkThemeBackground = Color(0xFF000000)
val darkThemeSurface = Color(0xFF2C2C2C)
val darkThemeError = Color(0xFFD32F2F)
val darkThemeErrorContainer = Color(0xFFFFCDD2)
val darkThemeOnErrorContainer = Color(0xFFB71C1C)

// Compose 'em light theme coloursssss
@Composable
fun lightColors() = lightColorScheme(
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
    onErrorContainer = lightThemeOnErrorContainer,
)

// repeat
@Composable
fun darkColors() = darkColorScheme(
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