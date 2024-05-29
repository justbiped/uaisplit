package com.biped.works.profile.ui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavHostController
import biped.works.compose.collectWithLifecycle
import biped.works.user.R
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.NormalSpacer
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.SingleLineTextField
import com.biped.locations.theme.components.SmallTitle
import com.biped.works.profile.SharedAnimationScope

data class ProfileUiModel(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val picture: String = ""
)

@Composable
internal fun SharedAnimationScope.ProfileScreen(
    viewModel: ProfileViewModel,
    navController: NavHostController,
) {
    val state by rememberProfileState()

    viewModel.uiState.collectWithLifecycle { instruction ->
        when (instruction) {
            is ProfileUiState.UpdateProfile -> state.updateState(instruction)
            is ProfileUiState.ProfileSaved -> state.showMessage(R.string.profile_saved_msg)
        }
    }

    val interactor = object : ProfileInteractor {
        override fun onSave() {
            viewModel.updateProfile(state.profile)
        }

        override fun onNameChange(name: String) {
            state.updateUser(name = name)
        }

        override fun onEmailChange(email: String) {
            state.updateUser(email = email)
        }

        override fun onNavigateUp() {
            navController.navigateUp()
        }
    }

    Box {
        if (state.viewState.isLoading) Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_LONG).show()
        if (state.messageRes > -1) {
            Toast.makeText(LocalContext.current, state.messageRes, Toast.LENGTH_LONG).show()
            state.messageRes = -1
        }
        ProfileUi(
            profile = state.profile,
            interactor = interactor
        )
    }
}

@Composable
private fun SharedAnimationScope.ProfileUi(
    profile: ProfileUiModel,
    interactor: ProfileInteractor
) {
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
                ProfileHeader(
                    name = profile.name,
                    imageUrl = profile.picture
                )
            }

            BigSpacer()

            Column(
                modifier = Modifier.weight(0.90f)
            ) {

                SingleLineTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = profile.name,
                    label = { Text(text = "name") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    onValueChange = { interactor.onNameChange(it) },
                )

                NormalSpacer()

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = profile.email,
                    label = { Text(text = "e-mail") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = { interactor.onEmailChange(it) },
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
    fun onNameChange(name: String) {}
    fun onEmailChange(email: String) {}
}

@Preview(showBackground = true)
@Composable
private fun ProfileUi_Preview(@PreviewParameter(ProfileUiModelDataProvider::class) profile: ProfileUiModel) {
    CashTheme {
        Box(Modifier.background(MaterialTheme.colorScheme.background)) {
            //            ProfileUi(
            //                profile = profile,
            //                interactor = object : ProfileInteractor {})
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileUi_Dark_Preview(@PreviewParameter(ProfileUiModelDataProvider::class) profile: ProfileUiModel) {
    ProfileUi_Preview(profile)
}

private class ProfileUiModelDataProvider : PreviewParameterProvider<ProfileUiModel> {
    override val values: Sequence<ProfileUiModel> = sequenceOf(
        ProfileUiModel(name = "Some User Name"),
        ProfileUiModel(name = "New User Name")
    )
}