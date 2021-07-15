package com.favoriteplaces

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.runner.Description

class InstantTaskRule(
    private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
) : InstantTaskExecutorRule() {

    override fun starting(description: Description?) {
        super.starting(description)
        DispatcherProvider.forceDispatcher(testDispatcher)
    }

    override fun finished(description: Description?) {
        DispatcherProvider.reset()
        super.finished(description)
    }
}