package com.biped.locations.user.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableWarmFlow
import com.biped.locations.user.settings.ObserveUseSettingsUseCase
import com.biped.locations.user.settings.SaveUserSettingsUseCase
import com.biped.locations.user.settings.data.UserSettings
import com.favoriteplaces.core.coroutines.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
internal class UserSettingsViewModel @Inject constructor(
    private val observeUseSettingsUseCase: ObserveUseSettingsUseCase,
    private val saveUserSettingsUseCase: SaveUserSettingsUseCase,
) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        loadUserSettings()
    }

    private fun loadUserSettings() {
        _instruction.post(Instruction.Loading)

        observeUseSettingsUseCase()
            .onEach { userSettings -> _instruction.post(Instruction.UpdateSettings(userSettings)) }
            .launchIn(viewModelScope)
    }

    fun changeThemeSettings(settings: UserSettings) {
        viewModelScope.launchIO {
            saveUserSettingsUseCase(settings)
        }
    }

    fun showUserProfile(userId: String) {
        _instruction.emit(Instruction.navigateToProfile(userId))
    }
}