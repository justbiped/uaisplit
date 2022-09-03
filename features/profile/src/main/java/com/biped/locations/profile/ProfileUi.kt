package com.biped.locations.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.theme.*
import com.biped.locations.theme.components.*

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
                    .weight(0.10f)
            ) {
                ProfileHeader(user)
            }
            BigSpacer()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.small)
                    .weight(0.90f)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(Dimens.normal)) {
                        MediumTitle(text = user.name)
                        TinySpacer()
                        MediumBody(text = user.imageUrl)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.big, end = Dimens.normal),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = { print("click") },
                    shape = MaterialTheme.shapes.small
                ) {
                    MediumTitle(text = "Add")
                }
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.normal),
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

@Preview(name = "Dark preview", showBackground = true)
@Composable
fun ProfileUiLightPreview() {
    AppTheme { ProfileUiStateless(user = userUiModel) }
}

@Preview(name = "Dark preview", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileUiDarkPreview() {
    AppTheme { ProfileUiStateless(user = userUiModel) }
}

val userUiModel = UserUiModel("Roubert Edgar")