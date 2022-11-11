package com.favoriteplaces.location.detail

import com.biped.test.unit.mock
import com.favoriteplaces.location.data.LocationRepository
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetLocationDetailsTest {

    private lateinit var getLocationDetails: GetLocationDetails

    private val repository = mock<LocationRepository>()

    @Before
    fun setUp() {
        getLocationDetails = GetLocationDetails(repository)
    }

    @Test
    fun `returns success result with location detail when location detail fetch was successful`() {
        runBlocking {
            val locationDetail = getLocationDetail()
            coEvery { repository.findLocationById(0) } returns locationDetail

            val result = getLocationDetails(0)

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(locationDetail)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch location detail was failed`() {
        runBlocking {
            val exception = Throwable("")
            coEvery { repository.findLocationById(0) } throws exception

            val result = getLocationDetails(0)

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
            Schedule(listOf(DaySchedule(Day.MONDAY, "10h", "11h")))
        )
}