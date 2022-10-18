package com.biped.locations.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.profile.R
import com.biped.locations.settings.ThemeSettings
import com.biped.locations.settings.ui.ThemeSettingsUi
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.BoxSurface
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.theme.components.MediumHeadline
import com.biped.locations.user.profile.data.User
import com.biped.locations.user.settings.data.UserSettings
import com.favoriteplaces.core.compose.navigate

private data class ProfileState(
    val isLoading: Boolean = false,
    val settings: UserSettings = UserSettings()
) {
    fun defaultState() = copy(isLoading = false)
    fun loadingState() = copy(isLoading = true)
    fun successState(settings: UserSettings) = copy(settings = settings, isLoading = false)
}

@Composable
internal fun UserSettingsScreen(
    viewModel: UserSettingsViewModel,
    navController: NavHostController
) {
    var state by remember { mutableStateOf(ProfileState()) }

    LaunchedEffect(Unit) {
        viewModel.instruction.collect { instruction ->
            state = when (instruction) {
                is Instruction.Success -> state.successState(instruction.settings)
                is Instruction.Default -> state.defaultState()
                is Instruction.Loading -> state.loadingState()
                is Instruction.Navigate -> {
                    navController.navigate(direction = instruction.direction)
                    state.defaultState()
                }
            }
        }
    }

    val listener = object : ProfileEvents {
        override fun onProfileClicked(userId: String) {
            viewModel.showUserProfile(userId)
        }

        override fun onThemeSettingsChanged(settings: ThemeSettings) {
            viewModel.changeThemeSettings(state.settings.copy(theme = settings))
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
                ProfileHeader(
                    user = state.settings.user,
                    onClick = { profileEvents.onProfileClicked(it) })
            }

            BigSpacer()

            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                ThemeSettingsUi(
                    uiModel = state.settings.theme,
                    onSettingsChanged = { profileEvents.onThemeSettingsChanged(it) }
                )
            }
        }

        LoadingIndicator(isLoading = state.isLoading)
    }
}

@Composable
private fun ProfileHeader(user: User, onClick: (id: String) -> Unit) {
    val profileImagePainter = rememberAsyncImagePainter(
        model = user.picture,
        placeholder = painterResource(id = R.drawable.ic_profile_on)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(user.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = profileImagePainter,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        SmallSpacer()
        MediumHeadline(text = user.name, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun BoxScope.LoadingIndicator(isLoading: Boolean) {
    if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Preview(name = "Light preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    AppTheme { UserSettingsUi(state = composeState, object : ProfileEvents {}) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    AppTheme { UserSettingsUi(state = composeState, object : ProfileEvents {}) }
}

private val composeState =
    ProfileState(settings = UserSettings(user = User(id = "", picture = "", name = "R.Edgar")))

interface ProfileEvents {
    fun onProfileClicked(userId: String) {}
    fun onThemeSettingsChanged(settings: ThemeSettings) {}
}
