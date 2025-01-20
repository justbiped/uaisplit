package com.biped.works.profile

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

    var viewState by mutableStateOf(UpdateProfile())
        private set

    var profile: ProfileUiModel by mutableStateOf(viewState.uiModel)
        private set

    var messageRes by mutableStateOf(-1)

    fun updateState(viewState: UpdateProfile) {
        this.viewState = viewState
        profile = viewState.uiModel.copy(
            name = profile.name.ifEmpty { viewState.uiModel.name },
            email = profile.email.ifEmpty { viewState.uiModel.email }
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