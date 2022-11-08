package com.biped.locations.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.biped.locations.settings.ThemeSettings
import com.biped.locations.settings.ui.ThemeSettingsUi
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.BoxSurface
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.user.ProfileHeader
import com.biped.locations.user.settings.data.UserSettings
import com.favoriteplaces.core.compose.Destination
import com.favoriteplaces.core.compose.collectWithLifecycle
import com.favoriteplaces.core.compose.navigate

@Stable
private data class ProfileState(
    private val navController: NavHostController,
    private val settingsState: MutableState<UserSettings> = mutableStateOf(UserSettings()),
    private val isLoadingState: MutableState<Boolean> = mutableStateOf(false)
) {
    val settings: UserSettings get() = settingsState.value
    val isLoading: Boolean get() = isLoadingState.value

    fun defaultState() {
        isLoadingState.value = false
        settingsState.value = UserSettings()
    }

    fun loadingState() {
        isLoadingState.value = true
    }

    fun successState(userSettings: UserSettings) {
        isLoadingState.value = false
        settingsState.value = userSettings
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
            is Instruction.Success -> state.successState(instruction.settings)
            is Instruction.Default -> state.defaultState()
            is Instruction.Loading -> state.loadingState()
            is Instruction.Navigate -> state.navigate(instruction.destination)
        }
    }

    val listener = object : ProfileEvents {
        override fun onProfileClicked(userId: String) {
            viewModel.showUserProfile(userId)
        }

        override fun onThemeSettingsChanged(themeSettings: ThemeSettings) {
            viewModel.changeThemeSettings(state.settings.copy(theme = themeSettings))
        }
    }

    UserSettingsUi(state, listener)
}

@Composable
private fun UserSettingsUi(state: ProfileState, profileEvents: ProfileEvents) {
    BoxSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = Dimens.small)
        ) {
            BigSpacer()

            Column(
                modifier = Modifier.weight(0.10f)
            ) {
                ProfileHeader(user = state.settings.user,
                    onClick = { profileEvents.onProfileClicked(it) })
            }

            BigSpacer()

            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                ThemeSettingsUi(uiModel = state.settings.theme,
                    onSettingsChanged = { profileEvents.onThemeSettingsChanged(it) })
            }
        }

        LoadingIndicator(isLoading = state.isLoading)
    }
}

@Composable
private fun BoxScope.LoadingIndicator(isLoading: Boolean) {
    if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Preview(name = "Light preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    val navController = rememberNavController()
    AppTheme { UserSettingsUi(state = ProfileState(navController), object : ProfileEvents {}) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    val navController = rememberNavController()
    AppTheme { UserSettingsUi(state = ProfileState(navController), object : ProfileEvents {}) }
}

interface ProfileEvents {
    fun onProfileClicked(userId: String) {}
    fun onThemeSettingsChanged(themeSettings: ThemeSettings) {}
}
