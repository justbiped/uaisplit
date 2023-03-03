package biped.works.user.settings.ui

import biped.works.compose.Destination
import biped.works.coroutines.MutableViewStateFlow
import biped.works.user.ProfileDestination
import biped.works.user.settings.data.UserSettings

internal sealed interface Instruction {
    data class UpdateSettings(
        val settings: UserSettings = UserSettings(),
        val isLoading: Boolean = false
    ) : Instruction

    data class Navigate(val destination: Destination) : Instruction

    companion object {
        fun navigateToProfile(userId: String) =
            Navigate(ProfileDestination(userId))
    }
}

internal fun MutableViewStateFlow<Instruction>.update(
    action: Instruction.UpdateSettings.() -> Instruction.UpdateSettings
) {
    (value as? Instruction.UpdateSettings)?.also { value = action(it) }
}