package com.biped.test.unit

import android.annotation.SuppressLint
import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("RestrictedApi")
class CoroutineTestRule(
    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        DispatcherProvider.forceDispatcher(testDispatcher)
        Dispatchers.setMain(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description) {
        DispatcherProvider.reset()
        Dispatchers.resetMain()
        super.finished(description)
    }
}