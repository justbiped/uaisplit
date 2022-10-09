package com.biped.locations.user.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biped.locations.user.settings.LoadUserSettingsUseCase
import com.biped.locations.user.settings.SaveUserSettingsUseCase
import com.biped.locations.user.settings.data.UserSettingsUiModel
import com.biped.locations.user.settings.data.toDomainModel
import com.biped.locations.user.settings.data.toUiModel
import com.favoriteplaces.core.coroutines.MutableWarmFlow
import com.favoriteplaces.core.coroutines.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserSettingsViewModel @Inject constructor(
    private val loadUserSettingsUseCase: LoadUserSettingsUseCase,
    private val saveUserSettingsUseCase: SaveUserSettingsUseCase,
) : ViewModel() {

    private val _instruction =
        MutableWarmFlow<UserSettingsInstruction>(UserSettingsInstruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        loadUserSettings()
    }

    fun loadUserSettings() {
        viewModelScope.launchIO {
            _instruction.post(UserSettingsInstruction.Loading)

            loadUserSettingsUseCase().collect { userSettings ->
                _instruction.post(
                    UserSettingsInstruction.Success(userSettings.toUiModel())
                )
            }
        }
    }

    fun changeThemeSettings(settings: UserSettingsUiModel) {
        viewModelScope.launchIO {
            saveUserSettingsUseCase(settings.toDomainModel())
        }
    }
}