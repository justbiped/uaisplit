package com.biped.locations.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biped.locations.profile.data.ProfileUiModel
import com.favoriteplaces.core.coroutines.MutableWarmFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _instruction = MutableWarmFlow<ProfileViewInstruction>(ProfileViewInstruction.Default)
    val instruction = _instruction.toWarmFlow()

    fun loadProfile() {
        viewModelScope.launch {
            _instruction.post(
                ProfileViewInstruction.Success(
                    ProfileUiModel(
                        "Roubert Edgar",
                        "https://media-exp1.licdn.com/dms/image/C4D03AQFkXBIUIWdT2g/profile-displayphoto-shrink_800_800/0/1517979972037?e=1668038400&v=beta&t=xJeY7HQEtSnDNhByyvi7Mas26NtR5c0OlR-TqJMpc88"
                    )
                )
            )

        }
    }
}