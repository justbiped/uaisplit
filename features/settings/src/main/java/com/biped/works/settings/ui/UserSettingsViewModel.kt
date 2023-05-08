package com.biped.works.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
import biped.works.coroutines.launchIO
import com.biped.works.settings.ObserveUserSettingsUseCase
import com.biped.works.settings.SaveUserSettingsUseCase
import com.biped.works.settings.data.UserSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
internal class UserSettingsViewModel @Inject constructor(
    private val observeUseSettingsUseCase: ObserveUserSettingsUseCase,
    private val saveUserSettingsUseCase: SaveUserSettingsUseCase,
) : ViewModel() {

    private val _instruction = MutableUiStateFlow<Instruction>(Instruction.UpdateSettings())
    val instruction = _instruction.toUiStateFlow()

    init {
        loadUserSettings()
    }

    private fun loadUserSettings() {
        _instruction.update { copy(isLoading = true) }

        observeUseSettingsUseCase()
            .onEach { userSettings -> _instruction.update { copy(settings = userSettings, isLoading = false) } }
            .launchIn(viewModelScope)
    }

    fun changeThemeSettings(settings: UserSettings) {
        viewModelScope.launchIO {
            saveUserSettingsUseCase(settings)
        }
    }

    fun showUserProfile(userId: String) {
        _instruction.sendEvent(Instruction.navigateToProfile())
    }
}