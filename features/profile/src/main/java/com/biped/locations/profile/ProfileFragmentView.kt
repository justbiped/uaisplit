package com.biped.locations.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
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
import com.biped.locations.theme.Dimens
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.panel

@Preview(showSystemUi = true)
@Composable
fun ProfileView(@PreviewParameter(UserPreview::class) user: UserUiModel) {
    Column(
        modifier = Modifier
            .panel()
            .padding(top = Dimens.normal)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            ProfileHeader(user)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ) {
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
            .fillMaxWidth()
            .padding(vertical = Dimens.small),
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
        Text(text = user.name)
    }
}

class UserPreview : PreviewParameterProvider<UserUiModel> {
    override val values = sequenceOf(UserUiModel("Roubert Edgar"))
}