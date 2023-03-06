package com.biped.works.settings.data

data class UserSettings(
    val userId: String = "",
    val name: String = "",
    val picture: String = "",
    val theme: ThemeSettings = ThemeSettings()
)