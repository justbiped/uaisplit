package com.biped.locations.profile.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.dimensionResource
import com.biped.locations.profile.R

fun Modifier.panel() = Modifier.composed {
    padding(horizontal = dimensionResource(R.dimen.normal))
}