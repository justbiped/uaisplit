package biped.works.coroutiens.test

import biped.works.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

object CoroutineTestDispatcher {
    private val forcedDispatcherField = DispatcherProvider::class.java.getDeclaredField("reflectionForcedTestDispatcher").apply {
        isAccessible = true
    }

    fun forceDispatcher(dispatcher: CoroutineDispatcher) {
        forcedDispatcherField.set(DispatcherProvider, dispatcher)
    }

    fun reset() {
        forcedDispatcherField.set(DispatcherProvider, null)
    }
}