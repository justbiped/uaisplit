package com.biped.locations.user.settings.ui

import com.biped.locations.user.settings.data.UserSettingsUiModel

sealed interface UserSettingsViewInstruction {
    object Default : UserSettingsViewInstruction
    data class Success(val uiModel: UserSettingsUiModel) : UserSettingsViewInstruction
}