package com.biped.locations.user.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.user.ProfileHeader
import com.biped.locations.user.profile.data.User
import com.favoriteplaces.core.compose.collectWithLifecycle

private data class ComposeState(
    val user: User = User(),
    val isLoading: Boolean = false
) {
    fun default() = copy(isLoading = false)
    fun loading() = copy(isLoading = true)
    fun updateUser(user: User) = copy(user = user, isLoading = false)
}

@Composable
internal fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    var state by remember { mutableStateOf(ComposeState()) }

    viewModel.instruction.collectWithLifecycle { instruction ->
        state = when (instruction) {
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
            is Instruction.UpdateUser -> state.updateUser(instruction.user)
        }
    }

    ProfileUi(state = state)
}

@Composable
private fun ProfileUi(state: ComposeState) {
    Column(
        modifier = Modifier.padding(horizontal = Dimens.small)
    ) {
        BigSpacer()

        Column(
            modifier = Modifier.weight(0.10f)
        ) {
            ProfileHeader(user = state.user)
        }

        BigSpacer()

        Column(
            modifier = Modifier.weight(0.90f)
        ) {

        }
    }
}
