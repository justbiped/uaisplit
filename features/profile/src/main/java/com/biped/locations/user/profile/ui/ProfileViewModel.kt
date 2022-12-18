package com.biped.locations.user.profile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableWarmFlow
import com.biped.locations.user.ProfileDestination.Companion.USER_ID_ARG
import com.biped.locations.user.profile.LoadUserUseCase
import com.biped.locations.user.profile.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class ProfileViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val loadUser: LoadUserUseCase
) : ViewModel() {

    private val _instruction = MutableWarmFlow<Instruction>(Instruction.Default)
    val instruction = _instruction.toWarmFlow()

    init {
        val userId = stateHandle.get<String>(USER_ID_ARG).orEmpty()
        loadUserProfile(userId)
    }

    private fun loadUserProfile(userId: String) {
        viewModelScope.launch {
            loadUser(userId).collect { user ->
                _instruction.post(Instruction.UpdateUser(user))
            }
        }
    }

    fun saveUser(user: User) {

    }
}
