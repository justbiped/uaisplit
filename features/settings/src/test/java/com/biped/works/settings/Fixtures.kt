package com.biped.works.settings

import com.biped.locations.theme.ColorTheme
import com.biped.works.settings.data.ThemeSettings

fun userSettingsFixture(
    userId: String = "userId",
    name: String = "R. Edgar",
    picture: String = "",
    theme: ThemeSettings = themeFixture()
) = com.biped.works.settings.data.UserSettings(
    userId = userId,
    name = name,
    picture = picture,
    theme = theme
)

fun themeFixture(
    colorScheme: ColorTheme = ColorTheme.SYSTEM,
    useDynamicColors: Boolean = false
) = ThemeSettings(colorScheme, useDynamicColors)