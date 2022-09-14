package com.biped.locations.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biped.locations.profile.LoadProfileUseCase
import com.biped.locations.profile.SaveProfileUseCase
import com.biped.locations.profile.data.toDomainModel
import com.biped.locations.profile.data.toUiModel
import com.biped.locations.profile.data.ui.ProfileUiModel
import com.favoriteplaces.core.coroutines.MutableWarmFlow
import com.favoriteplaces.core.tools.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loadProfileUseCase: LoadProfileUseCase,
    private val saveProfileUseCase: SaveProfileUseCase,
) : ViewModel() {

    private val _instruction = MutableWarmFlow<ProfileViewInstruction>(ProfileViewInstruction.Default)
    val instruction = _instruction.toWarmFlow()

    fun loadProfile() {
        viewModelScope.launch(DispatcherProvider.IO) {
            loadProfileUseCase().collect { profile ->
                _instruction.post(ProfileViewInstruction.Success(profile.toUiModel()))
            }
        }
    }

    fun changeThemeSettings(settings: ProfileUiModel) {
        viewModelScope.launch(DispatcherProvider.IO) {
            _instruction
            saveProfileUseCase(settings.toDomainModel())
        }
    }
}