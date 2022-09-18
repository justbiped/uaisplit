package com.favoriteplaces.core.coroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

open class WarmFlow<T>(private val initialValue: T) : Flow<T> {
    private val hotFlow = MutableStateFlow(initialValue)
    private val coldFlow = MutableSharedFlow<T>(replay = 0)

    internal open var value: T
        get() = hotFlow.value
        set(value) {
            hotFlow.value = value
        }

    init {
        runBlocking { hotFlow.emit(initialValue) }
    }

    override suspend fun collect(collector: FlowCollector<T>) {
        merge(hotFlow, coldFlow).collect(collector)
    }

    internal open suspend fun emit(value: T) {
        coldFlow.emit(value)
    }

    internal open fun post(value: T) {
        hotFlow.value = value
    }

    internal open fun reset() {
        hotFlow.value = initialValue
    }
}

class MutableWarmFlow<T>(value: T) : WarmFlow<T>(value) {
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

    public override fun reset() {
        super.reset()
    }

    fun toWarmFlow(): WarmFlow<T> = this
}