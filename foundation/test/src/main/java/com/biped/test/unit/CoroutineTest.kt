package com.biped.test.unit

import com.biped.test.unit.Assertions.assertThat
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.ListAssert
import org.assertj.core.api.ObjectAssert

fun test(
    context: CoroutineContext = UnconfinedTestDispatcher(),
    block: suspend CoroutineScope.() -> Unit
) {
    runTest(context = context, testBody = block)
}

fun runFlowTest(
    context: CoroutineContext = UnconfinedTestDispatcher(),
    block: suspend CoroutineScope.() -> FlowTest<*>
) {
    runTest(context = context) { block().finish() }
}

class FlowTest<T>(
    coroutineContext: CoroutineContext = UnconfinedTestDispatcher(),
    flow: Flow<T>
) {

    private val events = mutableListOf<T>()
    private val collectScope = CoroutineScope(context = coroutineContext)
    private var collectJob: Job = collectScope.launch { }

    init {
        flow.onEach { events.add(it) }
            .launchIn(CoroutineScope(coroutineContext))
    }

    fun assertEvent(): Assertion<T> {
        return assertThat(events[events.lastIndex])
    }

    fun assertEventAt(position: Int): Assertion<T> {
        return assertThat(events[position])
    }

    fun assertEvents(): ListAssert<T> {
        return org.assertj.core.api.Assertions.assertThat(events)
    }

    fun finish() {
        collectJob.cancel()
    }
}

fun <T> Flow<T>.test(context: CoroutineContext = UnconfinedTestDispatcher()) =
    FlowTest(context, this)

object Assertions {
    fun <T> assertThat(value: T) = Assertion(value)
}

open class Assertion<ACTUAL>(val value: ACTUAL) :
    AbstractObjectAssert<ObjectAssert<ACTUAL>, ACTUAL>(value, Assertion::class.java) {

    inline fun <reified T> isInstanceOf(): Assertion<T> {
        isInstanceOf(T::class.java)
        return Assertion(value as T)
    }
}
