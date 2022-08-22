package com.biped.test.unit

import io.mockk.mockk

inline fun <reified T : Any> mock(): T {
    return mockk(relaxed = true)
}