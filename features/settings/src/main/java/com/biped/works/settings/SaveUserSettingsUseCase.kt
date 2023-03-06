package com.biped.works.settings

import com.biped.works.settings.data.SettingsRepository
import com.biped.works.settings.data.UserSettings
import javax.inject.Inject

class SaveUserSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(userSettings: UserSettings) {
        settingsRepository.saveThemeSettings(userSettings.theme)
    }
}