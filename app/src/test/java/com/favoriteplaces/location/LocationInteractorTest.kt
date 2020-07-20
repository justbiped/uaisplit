package com.favoriteplaces.location

import com.favoriteplaces.location.data.LocationRepository
import com.favoriteplaces.location.list.data.Location
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

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