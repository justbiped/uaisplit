package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.flow.*

open class WarmFlow<T> : Flow<T> {
    private val hotFlow = MutableSharedFlow<T>(replay = 1)
    private val coldFlow = MutableSharedFlow<T>(replay = 0)

    internal open val value: T? = hotFlow.replayCache.firstOrNull()

    override suspend fun collect(collector: FlowCollector<T>) {
        merge(hotFlow, coldFlow).collect(collector)
    }

    internal open suspend fun emit(value: T) {
        coldFlow.emit(value)
    }

    internal open suspend fun post(value: T) {
        hotFlow.emit(value)
    }
}

class MutableWarmFlow<T> : WarmFlow<T>() {
    public override val value: T? get() = super.value

    public override suspend fun emit(value: T) {
        super.emit(value)
    }

    public override suspend fun post(value: T) {
        super.post(value)
    }

    fun toWarmFlow(): WarmFlow<T> = this
}