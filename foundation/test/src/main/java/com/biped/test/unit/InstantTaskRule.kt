package com.biped.test.unit

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.favoriteplaces.core.tools.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("RestrictedApi")
class InstantTaskRule(
    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
) : InstantTaskExecutorRule() {

    override fun starting(description: Description) {
        DispatcherProvider.forceDispatcher(testDispatcher)
        super.starting(description)
    }

    override fun finished(description: Description) {
        DispatcherProvider.reset()
        super.finished(description)
    }
}