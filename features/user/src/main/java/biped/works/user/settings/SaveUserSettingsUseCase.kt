package biped.works.user.settings

import biped.works.user.settings.data.SettingsRepository
import biped.works.user.settings.data.UserSettings
import javax.inject.Inject

class SaveUserSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(userSettings: UserSettings) {
        settingsRepository.saveThemeSettings(userSettings.theme)
    }
}