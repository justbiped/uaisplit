package com.favoriteplaces.core.tools

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class InstantTaskRule
    (private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)

        Dispatchers.setMain(testDispatcher)

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun postToMainThread(runnable: Runnable) = runnable.run()

            override fun isMainThread() = true
        })
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}