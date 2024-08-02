package biped.works.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    private var forcedDispatcher: CoroutineDispatcher? = null

    val IO: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.IO
    val Default: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Default
    val Main: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Main
    val Unconfined: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Unconfined
}