package com.biped.works.profile.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableUiStateFlow
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

    private val _uiState = MutableUiStateFlow<ProfileUiState>(ProfileUiState.UpdateProfile())
    val uiState = _uiState.toUiStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        observeProfile()
            .onEach { profile -> _uiState.update { copy(uiModel = profile.toUiModel()) } }
            .launchIn(viewModelScope)
    }

    fun updateProfile(profile: ProfileUiModel) {
        _uiState.update { copy(isLoading = true) }

        viewModelScope.launchIO {
            saveUser(profile.toDomain())
                .onSuccess {
                    _uiState.update { copy(isLoading = false) }
                    _uiState.sendEvent(ProfileUiState.ProfileSaved)
                }
                .onFailure {
                    Log.d("COROUTINE_TEST", "view model job failure")
                    print("")
                }
        }
    }
}
