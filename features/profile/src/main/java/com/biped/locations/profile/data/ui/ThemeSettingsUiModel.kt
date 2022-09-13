package com.biped.locations.profile.data.ui

import com.biped.locations.theme.ColorScheme

data class ThemeSettingsUiModel(
    val colorScheme: ColorScheme = ColorScheme.SYSTEM,
    val useDynamicColors: Boolean = false
)