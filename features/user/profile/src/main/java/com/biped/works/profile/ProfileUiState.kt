package com.biped.works.profile

data class UpdateProfile(
    val uiModel: ProfileUiModel = ProfileUiModel(),
    val isLoading: Boolean = false
)

sealed interface ProfileEvent {
    object ProfileSaved : ProfileEvent
}