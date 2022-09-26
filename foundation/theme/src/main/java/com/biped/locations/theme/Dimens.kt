package com.biped.locations.theme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.TinySpacer() {
    Spacer(modifier = Modifier.padding(end = Dimens.tiny))
}

@Composable
fun RowScope.SmallSpacer() {
    Spacer(modifier = Modifier.padding(end = Dimens.small))
}

@Composable
fun RowScope.NormalSpacer() {
    Spacer(modifier = Modifier.padding(end = Dimens.normal))
}

@Composable
fun RowScope.BigSpacer() {
    Spacer(modifier = Modifier.padding(end = Dimens.big))
}

@Composable
fun RowScope.HugeSpacer() {
    Spacer(modifier = Modifier.padding(end = Dimens.huge))
}

@Composable
fun ColumnScope.TinySpacer() {
    Spacer(modifier = Modifier.padding(top = Dimens.tiny))
}

@Composable
fun ColumnScope.SmallSpacer() {
    Spacer(modifier = Modifier.padding(top = Dimens.small))
}

@Composable
fun ColumnScope.NormalSpacer() {
    Spacer(modifier = Modifier.padding(top = Dimens.normal))
}

@Composable
fun ColumnScope.BigSpacer() {
    Spacer(modifier = Modifier.padding(top = Dimens.big))
}

@Composable
fun ColumnScope.HugeSpacer() {
    Spacer(modifier = Modifier.padding(top = Dimens.huge))
}

object Dimens {
    val micro: Dp @Composable get() = dimensionResource(id = R.dimen.micro)
    val tiny: Dp @Composable get() = dimensionResource(id = R.dimen.tiny)
    val small: Dp @Composable get() = dimensionResource(id = R.dimen.small)
    val normal: Dp @Composable get() = dimensionResource(id = R.dimen.normal)
    val big: Dp @Composable get() = dimensionResource(id = R.dimen.big)
    val huge: Dp @Composable get() = dimensionResource(id = R.dimen.huge)
}