package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.merge

open class WarmFlow<T>(
    initialValue: T,
    conflate: Boolean
) : Flow<T> {
    private val hotFlow: MutableSharedFlow<T>
    private val coldFlow: MutableSharedFlow<T>
    private val mergedFlow: Flow<T>

    internal open val value: T
        get() = hotFlow.replayCache.first()

    init {
        val onBufferOverflow = if (conflate.not()) {
            BufferOverflow.SUSPEND
        } else {
            BufferOverflow.DROP_OLDEST
        }

        coldFlow = MutableSharedFlow()
        hotFlow = MutableSharedFlow(replay = 1, onBufferOverflow = onBufferOverflow)
        mergedFlow = merge(
            hotFlow.distinctUntilChanged(),
            coldFlow.distinctUntilChanged()
        )

        hotFlow.tryEmit(initialValue)
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        mergedFlow.collect(collector)
    }

    internal open suspend fun emit(value: T) {
        coldFlow.emit(value)
    }

    internal open suspend fun post(value: T) {
        hotFlow.emit(value)
    }

    internal open suspend fun update(action: (value: T) -> T) {
        hotFlow.emit(action(value))
    }
}

class MutableWarmFlow<T>(value: T, conflate: Boolean = false) : WarmFlow<T>(value, conflate) {
    public override val value: T get() = super.value

    public override suspend fun emit(value: T) {
        super.emit(value)
    }

    public override suspend fun post(value: T) {
        super.post(value)
    }

    public override suspend fun update(action: (value: T) -> T) {
        super.update(action)
    }

    fun toWarmFlow(): WarmFlow<T> = this
}