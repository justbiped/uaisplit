package com.biped.works.settings.data

import com.biped.locations.theme.ColorTheme
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class SettingsRepository @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) {

    val themeSettingsStream: Flow<ThemeSettings> = combine(
        settingsDataStore.dynamicColorsSettings(),
        settingsDataStore.colorSchemeSettings().map { name -> toColorSettings(name) }
    ) { useDynamicColors, colorScheme ->
        ThemeSettings(
            colorScheme = colorScheme,
            useDynamicColors = useDynamicColors
        )
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