package com.favoriteplaces.location.detail

import com.favoriteplaces.location.LocationRepository
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedules
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test


class GetLocationDetailsTest {

    private lateinit var getLocationDetails: GetLocationDetails

    @MockK
    lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getLocationDetails = GetLocationDetails(repository)
    }

    @Test
    fun `returns success result with location detail when location detail fetch was successful`() {
        runBlocking {
            val locationDetail = getLocationDetail()
            coEvery { repository.findLocationById(0) } returns locationDetail

            val result = getLocationDetails(0)

            Assertions.assertThat(result.isSuccess).isTrue()
            Assertions.assertThat(result.getOrNull()).isEqualTo(locationDetail)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch location detail was failed`() {
        runBlocking {
            val exception = Throwable("")
            coEvery { repository.findLocationById(0) } throws exception

            val result = getLocationDetails(0)

            Assertions.assertThat(result.isFailure).isTrue()
            Assertions.assertThat(result.exceptionOrNull()).isEqualTo(exception)
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