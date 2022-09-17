package com.biped.locations.profile.data.ui

import com.biped.locations.profile.settings.ColorSettings

data class ThemeSettingsUiModel(
    val colorScheme: ColorSettings = ColorSettings.SYSTEM,
    val useDynamicColors: Boolean = false
)