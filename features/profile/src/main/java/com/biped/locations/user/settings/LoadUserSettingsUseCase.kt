package com.biped.locations.user.settings

import com.biped.locations.user.settings.data.UserSettings
import com.biped.locations.settings.SettingsRepository
import com.biped.locations.user.profile.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class LoadUserSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<UserSettings> {
        return combine(
            userRepository.observeUser(), settingsRepository.observeThemeSettings()
        ) { user, themeSettings ->
            UserSettings(
                name = user.name,
                picture = user.picture,
                themeSettings = themeSettings
            )
        }
    }
}