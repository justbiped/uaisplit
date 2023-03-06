package com.biped.works.profile.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
internal class StateHolder {

    var viewState by mutableStateOf(Instruction.UpdateUser())
        private set

    var profile: ProfileUiModel by mutableStateOf(viewState.profile)
    private set

    var messageRes by mutableStateOf(-1)

    fun updateState(viewState: Instruction.UpdateUser) {
        this.viewState = viewState
        profile = viewState.profile.copy(
            name = profile.name.ifEmpty { viewState.profile.name },
            email = profile.email.ifEmpty { viewState.profile.email }
        )
    }

    fun updateUser(name: String = "", email: String = "") {
        profile = profile.copy(
            name = name.ifEmpty { profile.name },
            email = email.ifEmpty { profile.email }
        )
    }

    fun showMessage(@StringRes messageRes: Int) {
        this.messageRes = messageRes
    }

    companion object {
        val saver = run {
            mapSaver(
                save = {
                    mapOf(
                        "name" to it.profile.name,
                        "email" to it.profile.email
                    )
                },

                restore = {
                    StateHolder().apply {
                        profile = profile.copy(name = it["name"].toString(), email = it["email"].toString())
                    }
                }
            )
        }
    }
}

@Composable
internal fun rememberProfileState() = rememberSaveable(stateSaver = StateHolder.saver) {
    mutableStateOf(StateHolder())
}