package com.biped.locations.profile.settings

import com.biped.locations.profile.data.domain.ThemeSettings
import com.biped.locations.theme.ColorScheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    private fun toColorScheme(name: String): ColorScheme {
        return try {
            ColorScheme.valueOf(name)
        } catch (error: Throwable) {
            ColorScheme.SYSTEM
        }
    }

    suspend fun saveThemeSettings(themeSettings: ThemeSettings) {
        settingsDataSource.saveColorScheme(themeSettings.colorScheme.name)
        settingsDataSource.saveDynamicColorSettings(themeSettings.useDynamicColors)
    }
}