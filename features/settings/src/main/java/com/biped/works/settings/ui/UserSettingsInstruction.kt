package com.biped.works.settings.ui

import biped.works.coroutines.MutableUiStateFlow
import com.biped.works.settings.data.UserSettings

internal sealed interface UserSettingsInstruction {
    data class UserSettingsState(
        val settings: UserSettings = UserSettings(),
        val isLoading: Boolean = false,
        val isDynamicColorSupported: Boolean = false
    ) : UserSettingsInstruction

    data class Navigate(val destination: String) : UserSettingsInstruction

    companion object {
        fun navigateToProfile() = Navigate("http://biped.works/profile")
    }
}

internal fun MutableUiStateFlow<UserSettingsInstruction>.update(
    action: UserSettingsInstruction.UserSettingsState.() -> UserSettingsInstruction.UserSettingsState
) {
    (value as? UserSettingsInstruction.UserSettingsState)?.also { value = action(it) }
}