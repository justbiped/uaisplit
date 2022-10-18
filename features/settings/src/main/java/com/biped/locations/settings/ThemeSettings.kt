package com.biped.locations.settings

import com.biped.locations.theme.ColorTheme

data class ThemeSettings(
    val colorScheme: ColorTheme = ColorTheme.SYSTEM,
    val useDynamicColors: Boolean = false
)