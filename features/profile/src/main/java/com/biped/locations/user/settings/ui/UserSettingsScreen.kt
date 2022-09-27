package com.biped.locations.user.settings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.profile.R
import com.biped.locations.settings.ui.ThemeSettingsUi
import com.biped.locations.settings.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.BoxSurface
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.theme.components.MediumHeadline
import com.biped.locations.user.settings.data.UserSettingsUiModel

private data class ProfileState(
    val isLoading: Boolean = false,
    val uiModel: UserSettingsUiModel = UserSettingsUiModel()
) {
    fun defaultState() = copy(isLoading = false)
    fun loadingState() = copy(isLoading = true)
    fun successState(uiModel: UserSettingsUiModel) = copy(uiModel = uiModel, isLoading = false)
}

@Composable
fun UserSettingsScreen(viewModel: UserSettingsViewModel) {
    var state by rememberState(state = ProfileState())

    state = when (val instruction = collectInstruction(viewModel)) {
        is UserSettingsInstruction.Success -> state.successState(instruction.uiModel)
        is UserSettingsInstruction.Default -> state.defaultState()
        is UserSettingsInstruction.Loading -> state.loadingState()
    }

    UserSettingsUi(
        state,
        object : ProfileEvents {
            override fun onThemeSettingsChanged(settings: ThemeSettingsUiModel) {
                viewModel.changeThemeSettings(state.uiModel.copy(theme = settings))
            }
        }
    )
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
                ProfileHeader(state.uiModel)
            }
            BigSpacer()
            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                ThemeSettingsUi(
                    uiModel = state.uiModel.theme,
                    onSettingsChanged = { profileEvents.onThemeSettingsChanged(it) }
                )
            }
        }

        LoadingIndicator(isLoading = state.isLoading)
    }
}

@Composable
private fun ProfileHeader(user: UserSettingsUiModel) {
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

private val composeState = ProfileState(uiModel = UserSettingsUiModel("R.Edgar"))

interface ProfileEvents {
    fun onThemeSettingsChanged(settings: ThemeSettingsUiModel) {}
}

@Composable
private fun collectInstruction(viewModel: UserSettingsViewModel) =
    viewModel.instruction.collectAsState(UserSettingsInstruction.Default).value

@Composable
fun <T> rememberState(state: T): MutableState<T> {
    return remember { mutableStateOf(state) }
}