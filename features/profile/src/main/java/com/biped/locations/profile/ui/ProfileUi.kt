package com.biped.locations.profile.ui

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
import com.biped.locations.profile.data.ui.ProfileUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.BigSpacer
import com.biped.locations.theme.ColorScheme
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.theme.components.MediumHeadline

private data class ProfileComposeState(
    var isLoading: Boolean = false,
    var uiModel: ProfileUiModel = ProfileUiModel()
)

@Composable
fun ProfileUI(viewModel: ProfileViewModel) {
    val instruction = viewModel.instruction.collectAsState(ProfileViewInstruction.Default).value
    val state by remember { mutableStateOf(ProfileComposeState()) }

    when (instruction) {
        is ProfileViewInstruction.Success -> state.apply {
            uiModel = instruction.uiModel
            isLoading = false
        }
        is ProfileViewInstruction.Default -> state.apply {
            isLoading = false
        }
    }

    BoxSurface(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileUiStateless(
            state, object : ProfileEvents {
                override fun useDynamicColors(use: Boolean) {
                    viewModel.setUseDynamicColors(use)
                }

                override fun onColorScheme(scheme: ColorScheme) {
                    viewModel.setColorScheme(scheme)
                }
            }
        )

        LoadingIndicator(isVisible = state.isLoading)
    }

}

@Composable
private fun BoxScope.LoadingIndicator(isVisible: Boolean) {
    if (isVisible) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
}

@Composable
private fun ProfileUiStateless(user: ProfileComposeState, profileEvents: ProfileEvents) {
    Surface {
        Column(
            modifier = Modifier.padding(horizontal = Dimens.small)
        ) {
            BigSpacer()
            Column(
                modifier = Modifier.weight(0.10f)
            ) {
                ProfileHeader(user.uiModel)
            }
            BigSpacer()
            Column(
                modifier = Modifier.weight(0.90f)
            ) {
                LargeLabel(text = "Theme setup")
                ThemeSettingsUi(
                    uiModel = user.uiModel.theme,
                    onUseDynamicScheme = { profileEvents.useDynamicColors(it) },
                    onColorScheme = { profileEvents.onColorScheme(it) }
                )
            }
        }
    }
}

@Composable
fun ProfileHeader(user: ProfileUiModel) {
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

@Preview(name = "Dark preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    AppTheme { ProfileUiStateless(user = composeState, object : ProfileEvents {}) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    AppTheme { ProfileUiStateless(user = composeState, object : ProfileEvents {}) }
}

private val composeState = ProfileComposeState(uiModel = ProfileUiModel("Roubert Edgar"))

interface ProfileEvents {
    fun useDynamicColors(use: Boolean) {}
    fun onColorScheme(scheme: ColorScheme) {}
}