package com.biped.works.settings

import com.biped.works.settings.data.SettingsRepository
import com.biped.works.settings.data.UserSettings
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveUserSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val userRepository: biped.works.user.data.UserRepository
) {
    operator fun invoke(): Flow<UserSettings> {
        return combine(
            userRepository.userStream,
            settingsRepository.themeSettingsStream
        ) { user, themeSettings ->
            UserSettings(
                userId = user.id,
                name = user.name,
                picture = user.picture,
                theme = themeSettings
            )
        }
    }
}