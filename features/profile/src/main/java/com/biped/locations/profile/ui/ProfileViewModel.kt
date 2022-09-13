package com.biped.locations.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biped.locations.profile.LoadProfileUseCase
import com.biped.locations.profile.data.toUiModel
import com.biped.locations.theme.ColorScheme
import com.favoriteplaces.core.coroutines.MutableWarmFlow
import com.favoriteplaces.core.tools.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loadProfileUseCase: LoadProfileUseCase
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

    fun setUseDynamicColors(use: Boolean) {
        print(use)
    }

    fun setColorScheme(scheme: ColorScheme) {
        print(scheme)
    }
}