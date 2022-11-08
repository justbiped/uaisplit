package com.biped.locations.user.settings.ui

import com.biped.locations.user.ProfileDestination
import com.biped.locations.user.settings.data.UserSettings
import com.favoriteplaces.core.compose.Destination

internal sealed interface Instruction {
    data class Success(val settings: UserSettings) : Instruction
    data class Navigate(val destination: Destination) : Instruction
    object Loading : Instruction
    object Default : Instruction

    companion object {
        fun navigateToProfile(userId: String) =
            Navigate(ProfileDestination(userId))
    }
}