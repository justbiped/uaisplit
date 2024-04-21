package com.biped.locations.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    errorContainer = light_errorContainer,
    onError = light_onError,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseOnSurface = light_inverseOnSurface,
    inverseSurface = light_inverseSurface,
    inversePrimary = light_inversePrimary,
    surfaceTint = light_surfaceTint,
)

private val DarkColors = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    errorContainer = dark_errorContainer,
    onError = dark_onError,
    onErrorContainer = dark_onErrorContainer,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_outline,
    inverseOnSurface = dark_inverseOnSurface,
    inverseSurface = dark_inverseSurface,
    inversePrimary = dark_inversePrimary,
    surfaceTint = dark_surfaceTint,
)

@Composable
fun CashTheme(
    colorTheme: ColorTheme = ColorTheme.SYSTEM,
    useDynamicColors: Boolean = false,
    content: @Composable () -> Unit
) {
    val isDarkMode = when (colorTheme) {
        ColorTheme.SYSTEM -> isSystemInDarkTheme()
        ColorTheme.DARK -> true
        ColorTheme.LIGHT -> false
    }

    val colorScheme = if (useDynamicColors && isDynamicColorsSupported()) {
        getDynamicColorScheme(isDarkMode)
    } else {
        getAppColorScheme(isDarkMode)
    }

    MaterialTheme(
        content = content,
        colorScheme = colorScheme,
        shapes = AppShapes,
        typography = AppTypography
    )
}

fun getAppColorScheme(isDarkMode: Boolean): ColorScheme {
    return if (isDarkMode) DarkColors else LightColors
}

private fun isDynamicColorsSupported() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun getDynamicColorScheme(isDarkMode: Boolean): ColorScheme {
    return if (isDarkMode) {
        dynamicDarkColorScheme(LocalContext.current)
    } else {
        dynamicLightColorScheme(LocalContext.current)
    }
}

enum class ColorTheme {
    SYSTEM,
    LIGHT,
    DARK
}