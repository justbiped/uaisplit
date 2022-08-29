package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

open class WarmFlow<T>(private val initialValue: T) : Flow<T> {
    private val hotFlow = MutableSharedFlow<T>(replay = 1)
    private val coldFlow = MutableSharedFlow<T>(replay = 0)

    internal open val value: T get() = hotFlow.replayCache.first()

    init {
        runBlocking { hotFlow.emit(initialValue) }
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        merge(hotFlow, coldFlow).collect(collector)
    }

    internal open suspend fun emit(value: T) {
        coldFlow.emit(value)
    }

    internal open suspend fun post(value: T) {
        hotFlow.emit(value)
    }

    internal open suspend fun repost() {
        hotFlow.emit(value)
    }

    internal open suspend fun reset() {
        hotFlow.emit(initialValue)
    }
}

class MutableWarmFlow<T>(value: T) : WarmFlow<T>(value) {
    public override val value: T get() = super.value

    public override suspend fun emit(value: T) {
        super.emit(value)
    }

    public override suspend fun post(value: T) {
        super.post(value)
    }

    public override suspend fun repost() {
        super.repost()
    }

    public override suspend fun reset() {
        super.reset()
    }

    fun toWarmFlow(): WarmFlow<T> = this
}