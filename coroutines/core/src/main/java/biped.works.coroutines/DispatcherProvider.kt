package biped.works.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    private var reflectionForcedTestDispatcher: CoroutineDispatcher? = null

    val IO: CoroutineDispatcher get() = reflectionForcedTestDispatcher ?: Dispatchers.IO
    val Default: CoroutineDispatcher get() = reflectionForcedTestDispatcher ?: Dispatchers.Default
    val Main: CoroutineDispatcher get() = reflectionForcedTestDispatcher ?: Dispatchers.Main
    val Unconfined: CoroutineDispatcher get() = reflectionForcedTestDispatcher ?: Dispatchers.Unconfined
}