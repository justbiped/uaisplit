package com.biped.locations.user.profile.old

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import biped.works.compose.collectWithLifecycle
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.NormalSpacer
import com.biped.locations.user.ProfileHeader
import com.biped.locations.user.profile.data.User

private data class ComposeState(
    val user: User = User(),
    val isLoading: Boolean = false
) {
    var name by mutableStateOf(user.name)

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

@OptIn(ExperimentalMaterial3Api::class)
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

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                label = { Text(text = "name") },
                onValueChange = { newText ->
                    state.name = newText.trimStart()
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            NormalSpacer()

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                label = { Text(text = "e-mail") },
                onValueChange = { newText ->
                    state.name = newText.trimStart()
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileUi_Preview() {
    AppTheme {
        ProfileUi(state = ComposeState(User(name = "Some User Name")))
    }
}

