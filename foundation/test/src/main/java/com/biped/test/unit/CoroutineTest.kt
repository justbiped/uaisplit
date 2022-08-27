package com.biped.test.unit

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
fun flowTest(block: suspend CoroutineScope.() -> Job) {
    runTest(context = UnconfinedTestDispatcher()) { block().cancel() }
}


fun test(block: suspend CoroutineScope.() -> Unit) {
    runTest(context = UnconfinedTestDispatcher(), testBody = block)
}
