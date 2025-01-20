package com.biped.works.settings.ui

import com.biped.works.settings.data.UserSettings
import com.favoriteplaces.core.SystemCompliance

data class UserSettingsState(
    val settings: UserSettings = UserSettings(),
    val isLoading: Boolean = false,
    val isDynamicColorSupported: Boolean = SystemCompliance.isDynamicColorSupported()
)

internal sealed interface UserSettingsEvent {
    data class Navigate(val destination: String) : UserSettingsEvent

    companion object {
        fun navigateToProfile() = Navigate("http://biped.works/profile")
    }
}