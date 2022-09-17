package com.biped.locations.profile.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) {

    fun observeThemeSettings(): Flow<ThemeSettings> {

        return combine(
            settingsDataSource.dynamicColorsSettings(),
            settingsDataSource.colorSchemeSettings().map { name -> toColorScheme(name) }
        ) { useDynamicColors, colorScheme ->
            ThemeSettings(
                colorScheme = colorScheme,
                useDynamicColors = useDynamicColors
            )
        }
    }

    private fun toColorScheme(name: String): ColorSettings {
        return try {
            ColorSettings.valueOf(name)
        } catch (error: Throwable) {
            ColorSettings.SYSTEM
        }
    }

    suspend fun saveThemeSettings(themeSettings: ThemeSettings) {
        settingsDataSource.saveColorScheme(themeSettings.colorScheme.name)
        settingsDataSource.saveDynamicColorSettings(themeSettings.useDynamicColors)
    }
}