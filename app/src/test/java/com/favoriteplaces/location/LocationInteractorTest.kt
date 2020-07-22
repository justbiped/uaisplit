package com.favoriteplaces.location

import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedule
import com.favoriteplaces.location.detail.data.domain.Schedules
import com.favoriteplaces.location.list.data.Location
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.time.DayOfWeek

@RunWith(MockitoJUnitRunner::class)
class LocationInteractorTest {

    private lateinit var interactor: LocationInteractor

    @Mock
    lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        interactor = LocationInteractor(repository)
    }

    @Test
    fun `returns success result with locations list when fetch locations was successful`() {
        runBlocking {
            val locationList = getLocationList()
            whenever(repository.fetchLocations()).thenReturn(locationList)

            val result = interactor.loadLocations()

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(locationList)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch locations was failed`() {
        runBlocking {
            val exception = Throwable("")
            whenever(repository.fetchLocations()).then { throw exception }

            val result = interactor.loadLocations()

            assertThat(result.isFailure).isTrue()
            assertThat(result.exceptionOrNull()).isEqualTo(exception)
        }
    }

    @Test
    fun `returns success result with location detail when location detail fetch was successful`() {
        runBlocking {
            val locationDetail = getLocationDetail()
            whenever(repository.findLocationById(0)).thenReturn(locationDetail)

            val result = interactor.loadLocationDetails(0)

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(locationDetail)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch location detail was failed`() {
        runBlocking {
            val exception = Throwable("")
            whenever(repository.findLocationById(0)).then { throw exception }

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
                listOf(
                    Schedule(
                        DayOfWeek.valueOf("MONDAY"),
                        "10h",
                        "19h"
                    )
                )
            )

        )

    private fun getLocationList() = listOf(
        Location(
            0,
            "Some Place",
            3.5,
            "Pub"
        ),
        Location(
            1,
            "Some Other Place",
            3.5,
            "Bar"
        )
    )


}
