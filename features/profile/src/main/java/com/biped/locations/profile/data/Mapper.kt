package com.biped.locations.profile.data

import com.biped.locations.profile.data.domain.Profile
import com.biped.locations.profile.settings.ThemeSettings
import com.biped.locations.profile.data.ui.ProfileUiModel
import com.biped.locations.profile.data.ui.ThemeSettingsUiModel

fun Profile.toUiModel() = ProfileUiModel(
    name, picture,
    themeSettings.toUiModel()
)

fun ThemeSettings.toUiModel() = ThemeSettingsUiModel(
    colorScheme = colorScheme,
    useDynamicColors = useDynamicColors
)

fun ProfileUiModel.toDomainModel() = Profile(
    name = name,
    picture = picture,
    themeSettings = theme.toDomainModel()
)

fun ThemeSettingsUiModel.toDomainModel() = ThemeSettings(
    colorScheme = colorScheme,
    useDynamicColors = useDynamicColors
)