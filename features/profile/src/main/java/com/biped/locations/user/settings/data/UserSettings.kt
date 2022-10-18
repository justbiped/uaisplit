package com.biped.locations.user.settings.data

import com.biped.locations.settings.ThemeSettings
import com.biped.locations.user.profile.data.User

data class UserSettings(
    val user: User = User(),
    val theme: ThemeSettings = ThemeSettings()
)