package com.favoriteplaces.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope

@Composable
fun Launch(action: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit, action)
}