package com.biped.locations.user.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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

@Stable
private class ProfileState(userModel: User = User()) {
    var user by mutableStateOf(userModel)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun default() {
        isLoading = false
    }

    fun loading() {
        isLoading = true
    }

    fun updateUser(user: User) {
        this.user = user
    }
}

@Composable
private fun rememberProfileState() = remember {
    mutableStateOf(ProfileState())
}

@Composable
internal fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val state by rememberProfileState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.UpdateUser -> state.updateUser(instruction.user)
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
        }
    }

    ProfileUi(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileUi(state: ProfileState) {
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
                value = state.user.name,
                label = { Text(text = "name") },
                onValueChange = { newText ->
                    state.updateUser(state.user.copy(name = newText.trimStart()))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            NormalSpacer()

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.user.name,
                label = { Text(text = "e-mail") },
                onValueChange = { newText -> },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileUi_Preview() {
    AppTheme {
        ProfileUi(state = ProfileState(User(name = "Some User Name")))
    }
}