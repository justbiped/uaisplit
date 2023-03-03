package biped.works.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import biped.works.user.settings.data.ThemeSettings
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.components.LargeLabel
import biped.works.user.profile.ui.ProfileHeader
import biped.works.user.settings.data.UserSettings

@Stable
private data class StateHolder(
    private val navController: NavHostController,
) {
    var viewState by mutableStateOf(Instruction.UpdateSettings())

    fun updateSettings(viewState: Instruction.UpdateSettings) {
        this.viewState = viewState
    }

    fun navigate(route: Destination) {
        navController.navigate(route)
    }
}

@Composable
private fun rememberSettingsState(navigator: NavHostController) = remember {
    mutableStateOf(StateHolder(navigator))
}

@Composable
internal fun UserSettingsScreen(
    viewModel: UserSettingsViewModel, navController: NavHostController
) {
    val stateHolder by rememberSettingsState(navController)

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.UpdateSettings -> stateHolder.updateSettings(instruction)
            is Instruction.Navigate -> stateHolder.navigate(instruction.destination)
        }
    }

    val interactor = object : SettingsInteractor {
        override fun onProfileClicked(userId: String) {
            viewModel.showUserProfile(userId)
        }

        override fun onThemeSettingsChanged(themeSettings: ThemeSettings) {
            viewModel.changeThemeSettings(stateHolder.viewState.settings.copy(theme = themeSettings))
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        UserSettingsUi(userSettings = stateHolder.viewState.settings, interactor = interactor)
        LoadingIndicator(isLoading = stateHolder.viewState.isLoading)
    }
}

@Composable
private fun UserSettingsUi(userSettings: UserSettings, interactor: SettingsInteractor) {
    Column(
        modifier = Modifier.padding(horizontal = Dimens.small)
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
            ThemeSettingsUi(uiModel = userSettings.theme,
                onSettingsChanged = { interactor.onThemeSettingsChanged(it) })
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
    val navController = rememberNavController()
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            UserSettingsUi(
                userSettings = UserSettings(),
                object : SettingsInteractor {})
        }
    }
}

@Preview(name = "Dark Preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiLightPreview() {
    ProfileUi_Light_Preview()
}