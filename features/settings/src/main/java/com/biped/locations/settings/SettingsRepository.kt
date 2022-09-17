package com.biped.locations.settings

import com.biped.locations.theme.ColorTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) {

    fun observeThemeSettings(): Flow<ThemeSettings> {
        return combine(
            settingsDataStore.dynamicColorsSettings(),
            settingsDataStore.colorSchemeSettings().map { name -> toColorSettings(name) }
        ) { useDynamicColors, colorScheme ->
            ThemeSettings(
                colorScheme = colorScheme,
                useDynamicColors = useDynamicColors
            )
        }
    }

    private fun toColorSettings(name: String): ColorTheme {
        return try {
            ColorTheme.valueOf(name)
        } catch (error: Throwable) {
            ColorTheme.SYSTEM
        }
    }

    suspend fun saveThemeSettings(themeSettings: ThemeSettings) {
        settingsDataStore.saveColorScheme(themeSettings.colorScheme.name)
        settingsDataStore.saveDynamicColorSettings(themeSettings.useDynamicColors)
    }
}