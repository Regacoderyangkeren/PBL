package com.example.pbl.ui.theme

// Light and Dark More
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Material 3 Colourrrrrs
import androidx.compose.ui.graphics.Color

// Light :angel: Theme Colourssss

// biru normal
private val lightThemePrimary = Color(0xFF1A237E)
private val lightThemePrimaryContainer = Color(0xFFC5CAE9)
private val lightThemeOnPrimaryContainer = Color(0xFF0D1445)

// ungu janda
private val lightThemeSecondary = Color(0xFF6A1B9A)
private val lightThemeSecondaryContainer = Color(0xFFE1BEE7)
private val lightThemeOnSecondaryContainer = Color(0xFF2A083D)

// hijau :thumbsup:
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


// Dark :demon: Theme Colourssss

// blue but gey
private val darkThemePrimary = Color(0xFFFFC107)
private val darkThemePrimaryContainer = Color(0xFF4A3B00)
private val darkThemeOnPrimaryContainer = Color(0xFFFFF1C1)

// purple so soft it lowk feels like femboy-y
private val darkThemeSecondary = Color(0xFFFF9800)
private val darkThemeSecondaryContainer = Color(0xFF4A2600)
private val darkThemeOnSecondaryContainer = Color(0xFFFFE0B2)


// ykw? this green is fine, they are cool
private val darkThemeTertiary = Color(0xFFFF5722)
private val darkThemeTertiaryContainer = Color(0xFF3A1400)
private val darkThemeOnTertiaryContainer = Color(0xFFFFDAD1)


// hitam rahhhhh
private val darkThemeBackground = Color(0xFF121212)
private val darkThemeSurface = Color(0xFF1E1E1E)

// red if they are not so mad or idk
private val darkThemeError = Color(0xFFEF9A9A)
private val darkThemeErrorContainer = Color(0xFF5F2120)
private val darkThemeOnErrorContainer = Color(0xFFFFDAD6)


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
    onErrorContainer = lightThemeOnErrorContainer,
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