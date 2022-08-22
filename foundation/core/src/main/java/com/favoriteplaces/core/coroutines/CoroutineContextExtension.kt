package com.favoriteplaces.core.coroutines

import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch


public fun CoroutineScope.launchDefault(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(DispatcherProvider.Default, start, block)
}

public fun CoroutineScope.launchMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(DispatcherProvider.Main, start, block)
}

public fun CoroutineScope.launchIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(DispatcherProvider.IO, start, block)
}

public fun CoroutineScope.launchUnconfined(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    launch(DispatcherProvider.Unconfined, start, block)
}