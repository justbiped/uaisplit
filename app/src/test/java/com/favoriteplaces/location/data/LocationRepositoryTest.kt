package com.favoriteplaces.location.data

import com.favoriteplaces.location.Location
import com.favoriteplaces.location.list.data.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.LocationRemoteEntity
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryTest {

    @Mock
    lateinit var locationHttpClient: LocationHttpClient

    private lateinit var locationRepository: LocationRepository

    @Before
    fun setUp() {
        locationRepository = LocationRepository(locationHttpClient)
    }

    @Test
    fun `returns list of location when fetch location was successfully done`() {
        runBlocking {

            whenever(locationHttpClient.fetchLocations()).thenReturn(
                LocationListRemoteEntity(
                    listOf(LocationRemoteEntity(0, "Some Place", 3.4, "Pub"))
                )
            )

            val locations = locationRepository.fetchLocations()

            assertThat(locations).contains(Location(0, "Some Place", 3.4, "Pub"))
        }
    }

    @Test
    fun `exposes caught exception on fetch locations failure`() {
        runBlocking {
            whenever(locationHttpClient.fetchLocations()).then { throw Throwable("Failed to fetch locations") }

            assertThatThrownBy {
                runBlocking { locationRepository.fetchLocations() }
            }.hasMessage("Failed to fetch locations")
        }
    }
}