package com.biped.works.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import biped.works.coroutines.launchIO
import biped.works.coroutines.mutableEventFlow
import com.biped.works.settings.ObserveUserSettingsUseCase
import com.biped.works.settings.SaveUserSettingsUseCase
import com.biped.works.settings.data.ThemeSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class UserSettingsViewModel @Inject constructor(
    private val observeUseSettingsUseCase: ObserveUserSettingsUseCase,
    private val saveUserSettingsUseCase: SaveUserSettingsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserSettingsState())
    val uiState = _uiState.onSubscription { loadUserSettings() }.asUiState(viewModelScope)

    val uiEvent: Flow<UserSettingsEvent>
        field = mutableEventFlow<UserSettingsEvent>()

    private fun loadUserSettings() {
        _uiState.update { it.copy(isLoading = true) }

        observeUseSettingsUseCase()
            .onEach { userSettings -> _uiState.update { it.copy(settings = userSettings, isLoading = false) } }
            .launchIn(viewModelScope)
    }

    fun changeThemeSettings(theme: ThemeSettings) {
        val settings = _uiState.value.settings.copy(theme = theme)
        _uiState.update { it.copy(settings = settings) }
        viewModelScope.launchIO { saveUserSettingsUseCase(settings) }
    }

    fun showUserProfile(userId: String) {
        uiEvent.tryEmit(UserSettingsEvent.navigateToProfile())
    }
}