package com.biped.locations.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.profile.R
import com.biped.locations.user.settings.data.UserSettingsUiModel
import com.biped.locations.settings.ui.ThemeSettingsUi
import com.biped.locations.settings.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.theme.components.MediumHeadline

private data class ProfileState(
    var isLoading: Boolean = false,
    var uiModel: UserSettingsUiModel = UserSettingsUiModel()
)

@Composable
fun UserSettingsScreen(viewModel: UserSettingsViewModel) {
    val instruction = viewModel.instruction.collectAsState(UserSettingsViewInstruction.Default).value
    val state by remember { mutableStateOf(ProfileState()) }

    when (instruction) {
        is UserSettingsViewInstruction.Success -> state.apply {
            uiModel = instruction.uiModel
            isLoading = false
        }
        is UserSettingsViewInstruction.Default -> state.apply {
            isLoading = false
        }
    }

    BoxSurface(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileUiStateless(
            state, object : ProfileEvents {
                override fun onThemeSettingsChanged(settings: com.biped.locations.settings.ui.ThemeSettingsUiModel) {
                    viewModel.changeThemeSettings(state.uiModel.copy(theme = settings))
                }
            }
        )
    }

}

@Composable
private fun ProfileUiStateless(state: ProfileState, profileEvents: ProfileEvents) {
    BoxSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = Dimens.small)
        ) {
            BigSpacer()
            Column(
                modifier = Modifier.weight(0.10f)
            ) {
                ProfileHeader(state.uiModel)
            }
            BigSpacer()
            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                com.biped.locations.settings.ui.ThemeSettingsUi(
                    uiModel = state.uiModel.theme,
                    onSettingsChanged = { profileEvents.onThemeSettingsChanged(it) }
                )
            }
        }

        LoadingIndicator(isLoading = state.isLoading)
    }
}

@Composable
fun ProfileHeader(user: UserSettingsUiModel) {
    val profileImagePainter = rememberAsyncImagePainter(
        model = user.picture,
        placeholder = painterResource(id = R.drawable.ic_profile_on)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
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
fun BoxSurface(
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Surface { content() }
    }
}

@Composable
private fun BoxScope.LoadingIndicator(isLoading: Boolean) {
    if (isLoading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Preview(name = "Light preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    AppTheme { ProfileUiStateless(state = composeState, object : ProfileEvents {}) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    AppTheme { ProfileUiStateless(state = composeState, object : ProfileEvents {}) }
}

private val composeState = ProfileState(uiModel = UserSettingsUiModel("R.Edgar"))

interface ProfileEvents {
    fun onThemeSettingsChanged(settings: com.biped.locations.settings.ui.ThemeSettingsUiModel) {}
}