package com.biped.locations.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.panel() = Modifier.composed {
    padding(horizontal = Dimens.normal)
}