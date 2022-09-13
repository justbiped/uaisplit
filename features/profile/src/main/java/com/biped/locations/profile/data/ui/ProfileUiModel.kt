package com.biped.locations.profile.data.ui

data class ProfileUiModel(
    val name: String = "",
    val picture: String = "",
    val theme: ThemeSettingsUiModel = ThemeSettingsUiModel(),
)