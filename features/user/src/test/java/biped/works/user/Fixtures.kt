package com.biped.locations.user

import biped.works.user.settings.data.ThemeSettings
import com.biped.locations.theme.ColorTheme
import biped.works.user.profile.data.User
import biped.works.user.settings.data.UserSettings

fun userSettingsFixture(
    user: User = userFixture(),
    theme: ThemeSettings = themeFixture()
) = UserSettings(
    userId = user.id,
    name = user.name,
    picture = user.picture,
    theme = theme
)

fun userFixture(
    id: String = "userId",
    name: String = "Roubert Edgar",
    picture: String = "noPicture"
) = User(id, name, picture)

fun themeFixture(
    colorScheme: ColorTheme = ColorTheme.SYSTEM,
    useDynamicColors: Boolean = false
) = ThemeSettings(colorScheme, useDynamicColors)