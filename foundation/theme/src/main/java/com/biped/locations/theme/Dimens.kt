package com.biped.locations.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp

@Composable
fun TinySpacer() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.tiny)))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.small)))
}

@Composable
fun NormalSpacer() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.normal)))
}

object Dimens {
    val tiny: Dp @Composable get() = dimensionResource(id = R.dimen.tiny)
    val small: Dp @Composable get() = dimensionResource(id = R.dimen.small)
    val normal: Dp @Composable get() = dimensionResource(id = R.dimen.normal)
    val big: Dp @Composable get() = dimensionResource(id = R.dimen.big)
    val huge: Dp @Composable get() = dimensionResource(id = R.dimen.huge)
}