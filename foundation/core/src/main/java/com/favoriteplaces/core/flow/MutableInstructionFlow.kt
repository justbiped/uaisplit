package com.favoriteplaces.core.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.merge

class MutableInstructionFlow<T : Instruction?> : Flow<T> {
    private val hotFlow = MutableSharedFlow<T>(replay = 1)
    private val coldFlow = MutableSharedFlow<T>(replay = 0)

    val value: T? = hotFlow.replayCache.firstOrNull()

    override suspend fun collect(collector: FlowCollector<T>) {
        merge(hotFlow, coldFlow).collect(collector)
    }

    suspend fun emit(value: T) {
        if (value is BufferedInstruction) {
            hotFlow.emit(value)
        } else {
            coldFlow.emit(value)
        }
    }

    fun toInstructionFlow() = InstructionFlow(this)

}

class InstructionFlow<T : Instruction?> internal constructor(
    private val mutableInstructionFlow: MutableInstructionFlow<T>
) : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        mutableInstructionFlow.collect(collector)
    }
}

interface Instruction

interface BufferedInstruction : Instruction
