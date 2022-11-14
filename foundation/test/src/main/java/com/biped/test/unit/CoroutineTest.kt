package com.biped.test.unit

import com.google.common.truth.Fact.simpleFact
import com.google.common.truth.FailureMetadata
import com.google.common.truth.Subject
import com.google.common.truth.Truth.assertAbout
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assert_
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

const val SIX_SECONDS_MS = 6000L

fun runTest(
    context: CoroutineContext = UnconfinedTestDispatcher(),
    dispatchTimeoutMs: Long = SIX_SECONDS_MS,
    testBody: suspend CoroutineScope.() -> Unit
) {
    runTest(context = context, dispatchTimeoutMs = dispatchTimeoutMs, testBody = testBody)
}

class TestFlow<T>(
    context: CoroutineContext,
    coroutineScope: CoroutineScope,
    flow: Flow<T>
) {

    internal val events = mutableListOf<T>()
    private var collectJob: Job

    init {
        collectJob = coroutineScope.launch(context = context) { flow.collect { events.add(it) } }
    }

    fun finish() {
        collectJob.cancel()
    }
}

fun <T> Flow<T>.test(
    scope: CoroutineScope,
    context: CoroutineContext = UnconfinedTestDispatcher()
): TestFlow<T> {
    return TestFlow(context, scope, this)
}

class TestFlowSubject<T> private constructor(
    failureMetadata: FailureMetadata,
    private val flow: TestFlow<T>
) : Subject(failureMetadata, flow) {

    private val events = flow.events
    private val receivedInstances = events.map { it!!::class.java }

    fun lastEvent(): Subject {
        assertEventReceived()
        return assert_().that(events[events.lastIndex])
    }

    fun receivedEventsOf(vararg clazz: Class<*>) {
        assertEventReceived()
        assertThat(receivedInstances).containsAnyIn(clazz)
    }

    fun receivedExactlyEventsOf(vararg clazz: Class<*>) {
        assertEventReceived()
        assertThat(receivedInstances).containsExactlyElementsIn(clazz)
    }

    fun receivedEvents(vararg event: T) {
        assertEventReceived()
        assertThat(flow.events).containsAnyIn(event)
    }

    fun receivedExactlyEvents(vararg event: T) {
        assertEventReceived()
        assertThat(receivedInstances).containsExactlyElementsIn(event)
    }

    private fun assertEventReceived() {
        if (flow.events.isEmpty()) failWithActual(simpleFact("No events was received"))
    }

    companion object {
        private fun <T> factory() =
            Factory<TestFlowSubject<T>, TestFlow<T>> { data, flow -> TestFlowSubject(data, flow) }

        fun <T> assertThat(testFlow: TestFlow<T>): TestFlowSubject<T> {
            return assertAbout(factory<T>()).that(testFlow)
        }
    }
}