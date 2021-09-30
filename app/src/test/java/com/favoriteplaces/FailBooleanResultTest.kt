package com.favoriteplaces

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FailBooleanResultTest {
    @Test
    fun `test result cast to boolean issue`() {
        runBlocking {
            val result = FailBooleanResult().invoke()

            assert(result.getOrThrow())
        }
    }
}