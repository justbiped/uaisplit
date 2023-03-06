package com.biped.works.profile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableViewStateFlow
import biped.works.coroutines.launchIO
import com.biped.works.profile.ObserveProfileUseCase
import com.biped.works.profile.SaveProfileUseCase
import com.biped.works.profile.data.toDomain
import com.biped.works.profile.data.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val observeProfile: ObserveProfileUseCase,
    private val saveUser: SaveProfileUseCase
) : ViewModel() {

    private val _instruction = MutableViewStateFlow<Instruction>(Instruction.UpdateUser())
    val instruction = _instruction.toViewStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        observeProfile()
            .onEach { profile -> _instruction.update { copy(profile = profile.toUiModel()) } }
            .launchIn(viewModelScope)
    }

    fun updateProfile(profile: ProfileUiModel) {
        viewModelScope.launchIO {
            saveUser(profile.toDomain())
            _instruction.emit(Instruction.ProfileSaved)
        }
    }
}
