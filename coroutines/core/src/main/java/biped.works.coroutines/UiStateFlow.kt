package biped.works.coroutines

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge

open class UiStateFlow<T>(initialValue: T) : Flow<T> {

    protected val hotFlow: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val coldFlow: MutableSharedFlow<T> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val mergedFlow: Flow<T> = merge(hotFlow, coldFlow)

    init {
        hotFlow.tryEmit(initialValue)
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        mergedFlow.collect(collector)
    }

    internal open fun sendEvent(value: T) {
        coldFlow.tryEmit(value)
    }

    internal open fun post(value: T) {
        hotFlow.tryEmit(value)
    }
}

class MutableUiStateFlow<T>(value: T) : UiStateFlow<T>(value) {
    var value: T
        get() = hotFlow.replayCache.first()
        set(value) = post(value)

    public override fun post(value: T) {
        super.post(value)
    }

    public override fun sendEvent(value: T) {
        super.sendEvent(value)
    }

    fun toUiStateFlow(): UiStateFlow<T> = this
}