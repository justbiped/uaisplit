package com.biped.locations.user.settings.ui

import com.biped.locations.user.ProfileNavGraph
import com.biped.locations.user.settings.data.UserSettings
import com.favoriteplaces.core.compose.Direction

internal sealed interface Instruction {
    data class Success(val settings: UserSettings) : Instruction
    data class Navigate(val direction: Direction) : Instruction
    object Loading : Instruction
    object Default : Instruction

    companion object {
        fun navigateToProfile(userId: String) =
            Navigate(ProfileNavGraph.ProfileDirection(userId))
    }
}