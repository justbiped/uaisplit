package com.biped.works.profile.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.R
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.MediumHeadline

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun SharedTransitionScope.ProfileHeader(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String = "",
    onClick: () -> Unit = {},
    animatedScope: AnimatedVisibilityScope
) {
    val interactionSource = remember { MutableInteractionSource() }
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .placeholderMemoryCacheKey(imageUrl)
        .memoryCacheKey(imageUrl)
        .build()

    val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
        keyframes {
            durationMillis = 200
        }
    }

    Row(
        modifier = modifier
            .sharedBounds(
                rememberSharedContentState(key = imageUrl),
                animatedVisibilityScope = animatedScope,
                boundsTransform = boundsTransform
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageRequest,
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
    CashTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            //          ProfileHeader(name = "R. Edgar")
        }
    }
}

@Composable
@Preview(heightDp = 60, widthDp = 360, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ProfileHeader_Preview_Dark() {
    CashTheme {
        ProfileHeader_Preview()
    }
}