package com.biped.locations.user.settings.data

import com.biped.locations.settings.ThemeSettings

data class UserSettings(
    val name: String = "",
    val picture: String = "",
    val themeSettings: com.biped.locations.settings.ThemeSettings
)