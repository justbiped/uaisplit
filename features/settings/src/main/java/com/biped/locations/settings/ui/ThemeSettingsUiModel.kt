package com.biped.locations.settings.ui

import com.biped.locations.theme.ColorTheme

data class ThemeSettingsUiModel(
    val colorScheme: ColorTheme = ColorTheme.SYSTEM,
    val useDynamicColors: Boolean = false
)