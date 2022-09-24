package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge

open class WarmFlow<T>(
    initialValue: T,
    conflate: Boolean
) : Flow<T> {

    private val hotFlow: MutableSharedFlow<T>
    private val coldFlow: MutableSharedFlow<T>

    internal open var value: T
        get() = hotFlow.replayCache.first()
        set(value) {
            hotFlow.tryEmit(value)
        }

    init {
        val extraBufferCapacity = if (conflate.not()) 1 else 0
        hotFlow = MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = extraBufferCapacity,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        coldFlow = MutableSharedFlow()

        hotFlow.tryEmit(initialValue)
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        merge(hotFlow, coldFlow).collect(collector)
    }

    internal open suspend fun emit(value: T) {
        coldFlow.emit(value)
    }

    internal open fun post(value: T) {
        hotFlow.tryEmit(value)
    }

    internal open fun update(action: (value: T) -> T) {
        hotFlow.tryEmit(action(value))
    }
}

class MutableWarmFlow<T>(value: T, conflate: Boolean = false) : WarmFlow<T>(value, conflate) {
    public override var value: T
        get() = super.value
        set(value) {
            super.value = value
        }

    public override suspend fun emit(value: T) {
        super.emit(value)
    }

    public override fun post(value: T) {
        super.post(value)
    }

    public override fun update(action: (value: T) -> T) {
        super.update(action)
    }

    fun toWarmFlow(): WarmFlow<T> = this
}