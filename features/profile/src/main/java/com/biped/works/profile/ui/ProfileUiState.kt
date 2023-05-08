package com.biped.works.profile.ui

import biped.works.coroutines.MutableUiStateFlow

internal sealed interface ProfileUiState {
    data class UpdateProfile(
        val uiModel: ProfileUiModel = ProfileUiModel(),
        val isLoading: Boolean = false
    ) : ProfileUiState

    object ProfileSaved : ProfileUiState
}

internal fun MutableUiStateFlow<ProfileUiState>.update(
    action: ProfileUiState.UpdateProfile.() -> ProfileUiState.UpdateProfile
) {
    (value as? ProfileUiState.UpdateProfile)?.also { value = action(it) }
}