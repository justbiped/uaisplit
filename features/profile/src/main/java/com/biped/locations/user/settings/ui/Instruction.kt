package com.biped.locations.user.settings.ui

import biped.works.compose.Destination
import com.biped.locations.user.ProfileDestination
import com.biped.locations.user.settings.data.UserSettings

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