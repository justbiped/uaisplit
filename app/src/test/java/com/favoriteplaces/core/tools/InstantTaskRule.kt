package com.favoriteplaces.core.tools

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class InstantTaskRule
    (private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()) : InstantTaskExecutorRule() {

    override fun starting(description: Description?) {
        super.starting(description)
        DispatcherProvider.forceDispatcher(testDispatcher)
    }

    override fun finished(description: Description?) {
        DispatcherProvider.reset()
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}