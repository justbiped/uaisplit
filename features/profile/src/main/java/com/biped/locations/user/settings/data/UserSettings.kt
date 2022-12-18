package com.biped.locations.user.settings.data

import com.biped.locations.settings.ThemeSettings

data class UserSettings(
    val userId: String = "",
    val name: String = "",
    val picture: String = "",
    val theme: ThemeSettings = ThemeSettings()
)