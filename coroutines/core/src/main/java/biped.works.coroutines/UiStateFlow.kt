package biped.works.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

fun <T> Flow<T>.asUiState(coroutineScope: CoroutineScope): SharedFlow<T> {
    return shareIn(coroutineScope, SharingStarted.WhileSubscribed(3_000), 1)
}

fun <T> mutableEventFlow() = MutableSharedFlow<T>(extraBufferCapacity = 1)