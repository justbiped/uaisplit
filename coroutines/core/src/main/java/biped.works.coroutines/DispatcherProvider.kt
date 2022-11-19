package biped.works.coroutines

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
    private var forcedDispatcher: CoroutineDispatcher? = null

    val IO: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.IO
    val Default: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Default
    val Main: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Main
    val Unconfined: CoroutineDispatcher get() = forcedDispatcher ?: Dispatchers.Unconfined

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun forceDispatcher(dispatcher: CoroutineDispatcher) {
        forcedDispatcher = dispatcher
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun reset() {
        forcedDispatcher = null
    }
}