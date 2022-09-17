package com.biped.locations.profile.data.domain

import com.biped.locations.profile.settings.ThemeSettings

data class Profile(
    val name: String = "",
    val picture: String = "",
    val themeSettings: ThemeSettings
)