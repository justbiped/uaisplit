package com.biped.works.profile.ui

import android.content.res.Configuration
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.keyframes
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import biped.works.compose.animtation.LocalNavAnimatedVisibilityScope
import biped.works.compose.animtation.LocalSharedTransitionScope
import biped.works.compose.animtation.apply
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.biped.locations.theme.CashTheme
import com.biped.locations.theme.SmallSpacer
import com.biped.locations.theme.components.MediumHeadline

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String = "",
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .placeholderMemoryCacheKey(imageUrl)
        .memoryCacheKey(imageUrl)
        .build()

    with(LocalSharedTransitionScope.current) {
        val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        val boundsTransform = BoundsTransform { _, _ ->
            keyframes { durationMillis = 200 }
        }

        Row(
            modifier = modifier
                .apply {
                    if (this@with != null && animatedVisibilityScope != null) {
                        sharedBounds(
                            rememberSharedContentState(key = imageUrl),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = boundsTransform
                        )
                    } else this
                }
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
}

@Composable
@Preview(heightDp = 60, widthDp = 360)
fun ProfileHeader_Preview() {
    CashTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            ProfileHeader(name = "R. Edgar")
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