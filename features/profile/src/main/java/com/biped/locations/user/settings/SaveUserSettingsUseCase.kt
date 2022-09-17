package com.biped.locations.user.settings

import com.biped.locations.user.settings.data.UserSettings
import com.biped.locations.settings.SettingsRepository
import javax.inject.Inject

class SaveUserSettingsUseCase @Inject constructor(
    private val settingsRepository: com.biped.locations.settings.SettingsRepository
) {
    suspend operator fun invoke(userSettings: UserSettings) {
        settingsRepository.saveThemeSettings(userSettings.themeSettings)
    }
}