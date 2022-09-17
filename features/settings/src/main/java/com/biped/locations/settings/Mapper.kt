package com.biped.locations.settings

import com.biped.locations.settings.ui.ThemeSettingsUiModel

fun ThemeSettings.toUiModel() = ThemeSettingsUiModel(
    colorScheme = colorScheme,
    useDynamicColors = useDynamicColors
)

fun ThemeSettingsUiModel.toDomainModel() = ThemeSettings(
    colorScheme = colorScheme,
    useDynamicColors = useDynamicColors
)