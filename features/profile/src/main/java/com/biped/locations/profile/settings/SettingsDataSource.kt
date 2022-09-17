package com.biped.locations.profile.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val SETTINGS_DATASTORE = "settingsDataStore"
val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS_DATASTORE)
val COLOR_SCHEME = stringPreferencesKey("ColorScheme")
val DYNAMIC_COLORS = booleanPreferencesKey("DynamicColors")

class SettingsDataSource @Inject constructor(
    private val context: Context
) {

    fun dynamicColorsSettings(): Flow<Boolean> = context.settingsDataStore.data
        .map { preferences -> preferences[DYNAMIC_COLORS] ?: false }

    fun colorSchemeSettings(): Flow<String> = context.settingsDataStore.data
        .map { settings -> settings[COLOR_SCHEME].orEmpty() }

    suspend fun saveColorScheme(colorScheme: String) {
        context.settingsDataStore.edit { settings ->
            settings[COLOR_SCHEME] = colorScheme
        }
    }

    suspend fun saveDynamicColorSettings(useDynamicColors: Boolean) {
        context.settingsDataStore.edit { settings ->
            settings[DYNAMIC_COLORS] = useDynamicColors
        }
    }
}