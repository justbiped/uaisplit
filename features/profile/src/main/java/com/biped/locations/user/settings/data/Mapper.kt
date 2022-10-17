package com.biped.locations.user.settings.data

import com.biped.locations.settings.toDomainModel
import com.biped.locations.settings.toUiModel

fun UserSettings.toUiModel() = UserSettingsUiModel(
    "user-id-1235",
    name,
    picture,
    themeSettings.toUiModel()
)

fun UserSettingsUiModel.toDomainModel() = UserSettings(
    name = name,
    picture = picture,
    themeSettings = theme.toDomainModel()
)
