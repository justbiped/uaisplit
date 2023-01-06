package biped.works.user.settings.ui

import biped.works.compose.Destination
import biped.works.user.ProfileDestination
import biped.works.user.settings.data.UserSettings

internal sealed interface Instruction {
    data class UpdateSettings(val settings: UserSettings) : Instruction
    data class Navigate(val destination: Destination) : Instruction
    object Loading : Instruction
    object Default : Instruction

    companion object {
        fun navigateToProfile(userId: String) =
            Navigate(ProfileDestination(userId))
    }
}