package com.biped.works.settings.ui

import biped.works.coroutines.MutableInstructionFlow
import com.biped.works.settings.data.UserSettings

internal sealed interface Instruction {
    data class UpdateSettings(
        val settings: UserSettings = UserSettings(),
        val isLoading: Boolean = false
    ) : Instruction

    data class Navigate(val destination: String) : Instruction

    companion object {
        fun navigateToProfile() = Navigate("http://biped.works/profile")
    }
}

internal fun MutableInstructionFlow<Instruction>.update(
    action: Instruction.UpdateSettings.() -> Instruction.UpdateSettings
) {
    (value as? Instruction.UpdateSettings)?.also { value = action(it) }
}