package com.biped.locations.user

import com.biped.locations.settings.ThemeSettings
import com.biped.locations.theme.ColorTheme
import com.biped.locations.user.profile.data.User
import com.biped.locations.user.settings.data.UserSettings

fun userSettingsFixture(
    user: User = userFixture(),
    theme: ThemeSettings = themeFixture()
) = UserSettings(user, theme)

fun userFixture(
    id: String = "userId",
    name: String = "Roubert Edgar",
    picture: String = "noPicture"
) = User(id, name, picture)

fun themeFixture(
    colorScheme: ColorTheme = ColorTheme.SYSTEM,
    useDynamicColors: Boolean = false
) = ThemeSettings(colorScheme, useDynamicColors)