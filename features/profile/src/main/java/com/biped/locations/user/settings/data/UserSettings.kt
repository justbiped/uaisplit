package com.biped.locations.user.settings.data

data class UserSettings(
    val name: String = "",
    val picture: String = "",
    val themeSettings: com.biped.locations.settings.ThemeSettings
)