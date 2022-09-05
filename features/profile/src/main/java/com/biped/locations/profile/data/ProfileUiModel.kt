package com.biped.locations.profile.data

import com.biped.locations.profile.ui.ThemeSettingsUiModel

data class ProfileUiModel(
    val name: String = "",
    val profileUrl: String = "",
    val theme: ThemeSettingsUiModel = ThemeSettingsUiModel(),
)