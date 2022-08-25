package com.biped.locations.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.theme.NormalSpacer
import com.biped.locations.theme.SmallSpacer

@Preview()
@Composable
fun ProfileUi(@PreviewParameter(UserPreview::class) user: UserUiModel) {
    Column {
        NormalSpacer()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            ProfileHeader(user)
        }
        NormalSpacer()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
                .background(Color.Cyan)
        ) {
            Row(
                modifier = Modifier
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Red)
                        .fillMaxSize()
                        .weight(1f)
                )
                SmallSpacer()
                Box(
                    modifier = Modifier
                        .background(color = Color.Yellow)
                        .fillMaxSize()
                        .weight(1f)
                )
                SmallSpacer()
                Box(
                    modifier = Modifier
                        .background(color = Color.Blue)
                        .fillMaxSize()
                        .weight(1f)
                )
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
        Text(text = user.name)
    }
}

class UserPreview : PreviewParameterProvider<UserUiModel> {
    override val values = sequenceOf(UserUiModel("Roubert Edgar"))
}