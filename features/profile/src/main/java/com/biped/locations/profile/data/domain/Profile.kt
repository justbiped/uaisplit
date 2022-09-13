package com.biped.locations.profile.data.domain

data class Profile(
    val name: String = "",
    val picture: String = "",
    val themeSettings: ThemeSettings
)