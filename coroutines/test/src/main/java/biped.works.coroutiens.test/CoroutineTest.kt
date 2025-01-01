package biped.works.coroutiens.test

import com.google.common.truth.Fact.simpleFact
import com.google.common.truth.FailureMetadata
import com.google.common.truth.Subject
import com.google.common.truth.Subject.Factory
import com.google.common.truth.Truth.assertAbout
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assert_
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

const val SIX_SECONDS_MS = 6000L

fun runTest(
    context: CoroutineContext = EmptyCoroutineContext,
    dispatchTimeoutMs: Long = SIX_SECONDS_MS,
    testBody: suspend CoroutineScope.() -> Unit
) {
    runTest(context = context, dispatchTimeoutMs = dispatchTimeoutMs, testBody = testBody)
}

fun <T> CoroutineScope.testFlowOf(
    flow: Flow<T>,
    context: CoroutineContext = UnconfinedTestDispatcher()
): TestFlow<T> {
    return TestFlow(context, this, flow)
}

class TestFlow<T>(
    context: CoroutineContext,
    coroutineScope: CoroutineScope,
    flow: Flow<T>
) {

    private var collectJob: Job
    private val _events = mutableListOf<T>()
    val events: List<T> get() = _events

    init {
        collectJob = coroutineScope.launch(context = context) { flow.toList(_events) }
    }

    fun finish() {
        collectJob.cancel()
    }
}

class TestFlowSubject<T> private constructor(
    failureMetadata: FailureMetadata,
    flow: TestFlow<T>?
) : Subject(failureMetadata, flow) {

    private val events = flow?.events ?: throw Exception("Test flow cannot be null mdfk")
    private val receivedInstances = events.map { it!!::class.java }

    fun lastEvent(): Subject {
        assertEventReceived()
        return assert_().that(events[events.lastIndex])
    }

    fun hasCollectedInstanceOf(vararg clazz: Class<*>) {
        assertThat(receivedInstances).containsAnyIn(clazz)
    }

    fun hasCollectedExactlyInstanceOf(vararg clazz: Class<*>) {
        assertThat(receivedInstances).containsExactlyElementsIn(clazz)
    }

    fun hasCollected(vararg event: T) {
        assertThat(events).containsAnyIn(event)
    }

    fun hasCollectedExactly(vararg event: T) {
        assertThat(events).containsExactlyElementsIn(event)
    }

    private fun assertEventReceived() {
        if (events.isEmpty()) failWithoutActual(simpleFact("No events was received"))
    }

    companion object {
        private fun <T> factory() =
            Factory<TestFlowSubject<T>, TestFlow<T>> { data, flow -> TestFlowSubject(data, flow) }

        fun <T> assertThat(testFlow: TestFlow<T>): TestFlowSubject<T> {
            return assertAbout(factory<T>()).that(testFlow)
        }
    }
}