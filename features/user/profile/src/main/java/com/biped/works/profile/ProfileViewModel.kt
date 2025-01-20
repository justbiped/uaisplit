package com.biped.works.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.asUiState
import biped.works.coroutines.launchIO
import biped.works.user.ObserveUserUseCase
import biped.works.user.SaveProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.update

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    private val observeUser: ObserveUserUseCase,
    private val saveUser: SaveProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UpdateProfile())
    val uiState = _uiState.onSubscription { loadUserProfile() }.asUiState(viewModelScope)

    private val _uiEvent = MutableSharedFlow<ProfileEvent>(extraBufferCapacity = 1)
    val uiEvent: Flow<ProfileEvent> = _uiEvent

    init {
        print("init")
    }

    private fun loadUserProfile() {
        observeUser()
            .onEach { user -> _uiState.update { it.copy(uiModel = user.toProfile()) } }
            .launchIn(viewModelScope)
    }

    fun updateProfile(profile: ProfileUiModel) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launchIO {
            saveUser(profile.toUser())
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    _uiEvent.tryEmit(ProfileEvent.ProfileSaved)
                }
                .onFailure { _uiState.update { it.copy(isLoading = false) } }
        }
    }
}
