package com.biped.locations.user.settings.ui

import com.biped.locations.user.settings.data.UserSettingsUiModel

sealed interface UserSettingsInstruction {
    data class Success(val uiModel: UserSettingsUiModel) : UserSettingsInstruction
    object Loading : UserSettingsInstruction
    object Default : UserSettingsInstruction
}