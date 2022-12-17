package com.biped.locations.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import biped.works.compose.Destination
import biped.works.compose.collectWithLifecycle
import biped.works.compose.navigate
import com.biped.locations.settings.ThemeSettings
import com.biped.locations.settings.ui.ThemeSettingsUi
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.BoxSurface
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.user.ProfileHeader
import com.biped.locations.user.settings.data.UserSettings

@Stable
private data class ProfileState(
    private val navController: NavHostController,
) {
    var isLoading: Boolean by mutableStateOf(false)
        private set
    var settings: UserSettings by mutableStateOf(UserSettings())
        private set

    fun defaultState() {
        isLoading = false
    }

    fun loadingState() {
        isLoading = true
    }

    fun successState(userSettings: UserSettings) {
        isLoading = false
        settings = userSettings
    }

    fun navigate(route: Destination) {
        navController.navigate(route)
    }
}

@Composable
private fun rememberSettingsState(navigator: NavHostController) = remember {
    mutableStateOf(ProfileState(navigator))
}

@Composable
internal fun UserSettingsScreen(
    viewModel: UserSettingsViewModel, navController: NavHostController
) {
    val state by rememberSettingsState(navController)

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.UpdateSettings -> state.successState(instruction.settings)
            is Instruction.Default -> state.defaultState()
            is Instruction.Loading -> state.loadingState()
            is Instruction.Navigate -> state.navigate(instruction.destination)
        }
    }

    val interactor = object : SettingsInteractor {
        override fun onProfileClicked(userId: String) {
            viewModel.showUserProfile(userId)
        }

        override fun onThemeSettingsChanged(themeSettings: ThemeSettings) {
            viewModel.changeThemeSettings(state.settings.copy(theme = themeSettings))
        }
    }

    UserSettingsUi(state = state, interactor = interactor)
}

@Composable
private fun UserSettingsUi(state: ProfileState, interactor: SettingsInteractor) {
    BoxSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = Dimens.small)
        ) {
            BigSpacer()

            Column(
                modifier = Modifier.weight(0.10f)
            ) {
                ProfileHeader(
                    user = state.settings.user,
                    onClick = { interactor.onProfileClicked(it) })
            }

            BigSpacer()

            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                ThemeSettingsUi(uiModel = state.settings.theme,
                    onSettingsChanged = { interactor.onThemeSettingsChanged(it) })
            }
        }

        LoadingIndicator(isLoading = state.isLoading)
    }
}

@Composable
private fun BoxScope.LoadingIndicator(isLoading: Boolean) {
    if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

private interface SettingsInteractor {
    fun onProfileClicked(userId: String) {}
    fun onThemeSettingsChanged(themeSettings: ThemeSettings) {}
}

@Preview(name = "Light preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    val navController = rememberNavController()
    AppTheme { UserSettingsUi(state = ProfileState(navController), object : SettingsInteractor {}) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    val navController = rememberNavController()
    AppTheme { UserSettingsUi(state = ProfileState(navController), object : SettingsInteractor {}) }
}