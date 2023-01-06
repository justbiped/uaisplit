package biped.works.user.settings

import biped.works.user.settings.data.SettingsRepository
import biped.works.user.profile.data.UserRepository
import biped.works.user.settings.data.UserSettings
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
                userId = user.id,
                name = user.name,
                picture = user.picture,
                theme = themeSettings
            )
        }
    }
}