package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge

open class WarmFlow<T>(
    initialValue: T
) : Flow<T> {
    protected val hotFlow: MutableSharedFlow<T> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val coldFlow: MutableSharedFlow<T> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val mergedFlow: Flow<T> = merge(hotFlow, coldFlow)

    init {

        hotFlow.tryEmit(initialValue)
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        coldFlow.resetReplayCache()
        mergedFlow.collect(collector)
    }

    internal open fun emit(value: T) {
        coldFlow.tryEmit(value)
    }

    internal open fun post(value: T) {
        hotFlow.tryEmit(value)
    }

    internal open fun update(action: (value: T) -> T) {
        hotFlow.tryEmit(action(hotFlow.replayCache.first()))
    }
}

class MutableWarmFlow<T>(value: T) : WarmFlow<T>(value) {
    var value: T
        get() = hotFlow.replayCache.first()
        set(value) = post(value)

    public override fun post(value: T) {
        super.post(value)
    }

    public override fun update(action: (value: T) -> T) {
        super.update(action)
    }

    public override fun emit(value: T) {
        super.emit(value)
    }

    fun toWarmFlow(): WarmFlow<T> = this
}