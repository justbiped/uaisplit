package com.biped.locations.profile

import com.biped.locations.profile.data.domain.Profile
import com.biped.locations.profile.data.ui.ProfileUiModel
import com.biped.locations.profile.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadProfileUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<Profile> {
        return settingsRepository.observeThemeSettings()
            .map { themeSettings ->
                Profile(
                    name = "Roubert Edgar",
                    picture = "https://media-exp1.licdn.com/dms/image/C4D03AQFkXBIUIWdT2g/profile-displayphoto-shrink_800_800/0/1517979972037?e=1668038400&v=beta&t=xJeY7HQEtSnDNhByyvi7Mas26NtR5c0OlR-TqJMpc88",
                    themeSettings = themeSettings
                )
            }
    }
}