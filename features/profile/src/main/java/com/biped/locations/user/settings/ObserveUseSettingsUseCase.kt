package com.biped.locations.user.settings

import com.biped.locations.settings.SettingsRepository
import com.biped.locations.user.profile.data.UserRepository
import com.biped.locations.user.settings.data.UserSettings
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveUseSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<UserSettings> {
        return combine(
            userRepository.userStream,
            settingsRepository.themeSettingsStream
        ) { user, themeSettings ->
            UserSettings(
                user = user,
                theme = themeSettings
            )
        }
    }
}