package biped.works.user.profile.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import biped.works.user.profile.data.User

@Stable
internal class StateHolder {

    var viewState by mutableStateOf(Instruction.UpdateUser())
        private set

    var user: User by mutableStateOf(viewState.user)
    private set

    var messageRes by mutableStateOf(-1)

    fun updateState(viewState: Instruction.UpdateUser) {
        this.viewState = viewState
        user = user.copy(
            name = user.name.ifEmpty { viewState.user.name },
            email = user.email.ifEmpty { viewState.user.email }
        )
    }

    fun updateUser(name: String = "", email: String = "") {
        user = user.copy(
            name = name.ifEmpty { user.name },
            email = email.ifEmpty { user.email }
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
                        "name" to it.user.name,
                        "email" to it.user.email
                    )
                },

                restore = {
                    StateHolder().apply {
                        user = user.copy(name = it["name"].toString(), email = it["email"].toString())
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