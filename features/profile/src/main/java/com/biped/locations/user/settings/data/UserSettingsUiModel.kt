package com.biped.locations.user.settings.data

import com.biped.locations.settings.ui.ThemeSettingsUiModel

data class UserSettingsUiModel(
    val name: String = "",
    val picture: String = "",
    val theme: com.biped.locations.settings.ui.ThemeSettingsUiModel = com.biped.locations.settings.ui.ThemeSettingsUiModel(),
)