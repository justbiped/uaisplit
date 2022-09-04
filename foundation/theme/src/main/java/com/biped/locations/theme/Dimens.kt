package com.biped.locations.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp

@Composable
fun TinySpacer() {
    Spacer(modifier = Modifier.size(Dimens.tiny))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(Dimens.small))
}

@Composable
fun NormalSpacer() {
    Spacer(modifier = Modifier.size(Dimens.normal))
}

@Composable
fun BigSpacer() {
    Spacer(modifier = Modifier.size(Dimens.big))
}

@Composable
fun HugeSpacer() {
    Spacer(modifier = Modifier.size(Dimens.huge))
}

object Dimens {
    val micro: Dp @Composable get() = dimensionResource(id = R.dimen.micro)
    val tiny: Dp @Composable get() = dimensionResource(id = R.dimen.tiny)
    val small: Dp @Composable get() = dimensionResource(id = R.dimen.small)
    val normal: Dp @Composable get() = dimensionResource(id = R.dimen.normal)
    val big: Dp @Composable get() = dimensionResource(id = R.dimen.big)
    val huge: Dp @Composable get() = dimensionResource(id = R.dimen.huge)
}