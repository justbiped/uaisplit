package com.biped.locations.profile.ui

import com.biped.locations.profile.data.ProfileUiModel

sealed interface ProfileViewInstruction {
    object Default : ProfileViewInstruction
    data class Success(val uiModel: ProfileUiModel) : ProfileViewInstruction
}