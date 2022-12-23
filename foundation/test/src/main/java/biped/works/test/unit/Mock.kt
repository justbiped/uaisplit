package biped.works.test.unit

import io.mockk.impl.annotations.MockK
import io.mockk.mockk

@MockK(relaxed = true)
annotation class Mock

inline fun <reified T : Any> mock(): T {
    return mockk(relaxed = true)
}