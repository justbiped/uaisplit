package com.biped.works.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import biped.works.compose.collectWithLifecycle
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeLabel
import com.biped.works.profile.ProfileHeader
import com.biped.works.settings.data.ThemeSettings
import com.biped.works.settings.data.UserSettings

@Composable
internal fun UserSettingsScreen(
    viewModel: UserSettingsViewModel,
    navController: NavHostController
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle(UserSettingsState())

    viewModel.uiEvent.collectWithLifecycle { event ->
        when (event) {
            is UserSettingsEvent.Navigate -> navController.navigate(event.destination)
        }
    }

    val interactor = object : SettingsInteractor {
        override fun onProfileClicked(userId: String) {
            viewModel.showUserProfile(userId)
        }

        override fun onThemeSettingsChanged(themeSettings: ThemeSettings) {
            viewModel.changeThemeSettings(themeSettings)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        UserSettingsUi(
            userSettings = state.value.settings,
            isDynamicColorSupported = state.value.isDynamicColorSupported,
            interactor = interactor
        )
        LoadingIndicator(isLoading = state.value.isLoading)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserSettingsUi(
    userSettings: UserSettings,
    isDynamicColorSupported: Boolean,
    interactor: SettingsInteractor
) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
            .padding(horizontal = Dimens.small)

    ) {
        BigSpacer()

        Column(
            modifier = Modifier.weight(0.10f)
        ) {
            ProfileHeader(
                name = userSettings.name,
                imageUrl = userSettings.picture,
                onClick = { interactor.onProfileClicked(userSettings.userId) }
            )
        }

        BigSpacer()

        Column(
            modifier = Modifier.weight(0.90f)
        ) {
            LargeLabel(text = "Theme setup")
            ThemeSettingsUi(
                uiModel = userSettings.theme,
                isDynamicColorSupported = isDynamicColorSupported,
                onSettingsChanged = { interactor.onThemeSettingsChanged(it) }
            )
        }
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

@Preview(name = "Light Preview")
@Composable
fun ProfileUi_Light_Preview() {
    CashTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            //            UserSettingsUi(
            //                userSettings = UserSettings(),
            //                object : SettingsInteractor {})
        }
    }
}

@Preview(name = "Dark Preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiLightPreview() {
    ProfileUi_Light_Preview()
}
