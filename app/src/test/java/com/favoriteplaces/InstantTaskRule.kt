package com.favoriteplaces

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.runner.Description

class InstantTaskRule(
    private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : InstantTaskExecutorRule() {

    override fun starting(description: Description?) {
        super.starting(description)
        DispatcherProvider.forceDispatcher(testDispatcher)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        DispatcherProvider.reset()
        Dispatchers.resetMain()
        super.finished(description)
    }
}