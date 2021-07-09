package com.favoriteplaces.location

import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedules
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationInteractorTest {

    private lateinit var interactor: LocationInteractor

    @MockK
    lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        interactor = LocationInteractor(repository)
    }

    @Test
    fun `returns success result with location detail when location detail fetch was successful`() {
        runBlocking {
            val locationDetail = getLocationDetail()
            coEvery { repository.findLocationById(0) } returns locationDetail

            val result = interactor.loadLocationDetails(0)

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(locationDetail)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch location detail was failed`() {
        runBlocking {
            val exception = Throwable("")
            coEvery { repository.findLocationById(0) } throws exception

            val result = interactor.loadLocationDetails(0)

            assertThat(result.isFailure).isTrue()
            assertThat(result.exceptionOrNull()).isEqualTo(exception)
        }
    }

    private fun getLocationDetail() =
        LocationDetail(
            0,
            "Some Place",
            3.5,
            "Pub",
            "About",
            "123",
            "address",
            Schedules(
                listOf(DaySchedule(Day.MONDAY, "10h", "19h"))
            )
        )
}
