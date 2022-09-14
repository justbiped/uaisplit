package com.biped.locations.profile

import com.biped.locations.profile.data.domain.Profile
import com.biped.locations.profile.settings.SettingsRepository
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(profile: Profile) {
        settingsRepository.saveThemeSettings(profile.themeSettings)
    }
}