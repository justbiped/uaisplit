package com.biped.locations.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.theme.*
import com.biped.locations.theme.components.MediumBody
import com.biped.locations.theme.components.MediumHeadline
import com.biped.locations.theme.components.MediumTitle

@Composable
fun ProfileUI(uiModel: UserUiModel) {
    ProfileUiStateless(user = uiModel)
}

@Composable
fun ProfileUiStateless(user: UserUiModel) {
    Surface {
        Column {
            BigSpacer()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.25f)
            ) {
                ProfileHeader(user)
            }
            NormalSpacer()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.small)
                    .weight(0.75f)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(Dimens.normal)) {
                        MediumTitle(text = user.name)
                        SmallSpacer()
                        MediumBody(text = user.imageUrl)
                    }
                }
            }
            FloatingActionButton(onClick = { print("click") }) {
            }
        }
    }
}

@Composable
fun ProfileHeader(user: UserUiModel) {
    val profileImagePainter = rememberAsyncImagePainter(
        model = user.imageUrl,
        placeholder = painterResource(id = R.drawable.ic_profile_on)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = profileImagePainter,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        SmallSpacer()
        MediumHeadline(text = user.name)
    }
}

@Preview(name = "Dark preview", showBackground = true)
@Composable
fun ProfileUiLightPreview(@PreviewParameter(UserPreview::class) uiModel: UserUiModel) {
    AppTheme {
        ProfileUiStateless(user = uiModel)
    }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview(@PreviewParameter(UserPreview::class) uiModel: UserUiModel) {
    AppTheme {
        ProfileUiStateless(user = uiModel)
    }
}

class UserPreview : PreviewParameterProvider<UserUiModel> {
    override val values = sequenceOf(UserUiModel("Roubert Edgar"))
}