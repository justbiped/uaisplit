package com.biped.test.unit

import android.annotation.SuppressLint
import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("RestrictedApi")
class CoroutineTestRule(
    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        DispatcherProvider.forceDispatcher(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description) {
        DispatcherProvider.reset()
        super.finished(description)
    }
}