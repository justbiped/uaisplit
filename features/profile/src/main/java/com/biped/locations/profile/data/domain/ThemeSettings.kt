package com.biped.locations.profile.data.domain

import com.biped.locations.theme.ColorScheme

data class ThemeSettings(
    val colorScheme: ColorScheme,
    val useDynamicColors: Boolean
)