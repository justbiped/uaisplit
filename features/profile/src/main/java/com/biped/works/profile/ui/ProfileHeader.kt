package com.biped.works.profile.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import biped.works.user.R
import coil.compose.rememberAsyncImagePainter
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.MediumHeadline

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String = "",
    onClick: () -> Unit = {}
) {
    val profileImagePainter = rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.ic_profile_on),
        error = painterResource(id = R.drawable.ic_profile_on),
    )
    Row(
        modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onClick()
            },
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
        MediumHeadline(text = name, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
@Preview(heightDp = 60, widthDp = 360)
fun ProfileHeader_Preview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            ProfileHeader(name = "R. Edgar")
        }
    }
}

@Composable
@Preview(heightDp = 60, widthDp = 360, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ProfileHeader_Preview_Dark() {
    AppTheme {
        ProfileHeader_Preview()
    }
}