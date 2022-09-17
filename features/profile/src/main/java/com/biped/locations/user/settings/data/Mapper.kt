package com.biped.locations.user.settings.data

import com.biped.locations.settings.toDomainModel
import com.biped.locations.settings.toUiModel

fun UserSettings.toUiModel() = UserSettingsUiModel(
    name, picture,
    themeSettings.toUiModel()
)

fun UserSettingsUiModel.toDomainModel() = UserSettings(
    name = name,
    picture = picture,
    themeSettings = theme.toDomainModel()
)
