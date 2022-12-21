package com.biped.locations.user.profile.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import biped.works.compose.collectWithLifecycle
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.NormalSpacer
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.SingleLineTextField
import com.biped.locations.theme.components.SmallTitle
import com.biped.locations.user.ProfileHeader
import com.biped.locations.user.profile.data.User

@Stable
private class ProfileState(uiModel: User = User()) {

    var user by mutableStateOf(uiModel)
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

    fun updateUser(update: User.() -> User) {
        user = update(user)
    }
}

@Composable
private fun rememberProfileState() = remember {
    mutableStateOf(ProfileState())
}

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel(), navController: NavHostController
) {
    val state by rememberProfileState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.UpdateUser -> state.updateUser(instruction.user)
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
        }
    }

    val interactor = object : ProfileInteractor {
        override fun onSave() {
            viewModel.updateUser(state.user)
        }

        override fun onNavigateUp() {
            navController.navigateUp()
        }
    }

    ProfileUi(state = state, interactor = interactor)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileUi(state: ProfileState, interactor: ProfileInteractor) {
    Column {
        TopAppbar(
            onNavigateUp = { interactor.onNavigateUp() },
            onSave = { interactor.onSave() })

        SmallSpacer()

        Column(
            modifier = Modifier.padding(horizontal = Dimens.small)
        ) {
            Column(
                modifier = Modifier.weight(0.10f)
            ) {
                ProfileHeader(name = state.user.name, imageUrl = state.user.picture)
            }

            BigSpacer()

            Column(
                modifier = Modifier.weight(0.90f)
            ) {

                SingleLineTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.user.name,
                    label = { Text(text = "name") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    onValueChange = { name -> state.updateUser { copy(name = name) } },
                )

                NormalSpacer()

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.user.email,
                    label = { Text(text = "e-mail") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = { email -> state.updateUser { copy(email = email) } },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppbar(
    onNavigateUp: () -> Unit, onSave: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Profile") },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                SmallTitle(text = "Save", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    )
}

private interface ProfileInteractor {
    fun onSave() {}
    fun onNavigateUp() {}
}

@Preview(showBackground = true)
@Composable
private fun ProfileUi_Preview() {
    AppTheme {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            ProfileUi(
                state = ProfileState(User(name = "Some User Name")),
                interactor = object : ProfileInteractor {})
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileUi_Dark_Preview() {
    ProfileUi_Preview()
}