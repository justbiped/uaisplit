package com.favoriteplaces.location.data

import com.favoriteplaces.location.LocationHttpClient
import com.favoriteplaces.location.LocationRepository
import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.remote.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.remote.LocationRemoteEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryTest {

    @MockK
    lateinit var locationHttpClient: LocationHttpClient

    private lateinit var locationRepository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        locationRepository = LocationRepository(locationHttpClient)
    }

    @Test
    fun `returns list of location when fetch location was successfully done`() {
        runBlocking {
            coEvery { locationHttpClient.fetchLocations() } returns LocationListRemoteEntity(
                listOf(LocationRemoteEntity(0, "Some Place", 3.4, "Pub"))
            )

            val locations = locationRepository.fetchLocations()

            assertThat(locations).contains(Location(0, "Some Place", 3.4, "Pub"))
        }
    }

    @Test
    fun `exposes caught exception on fetch locations failure`() {
        runBlocking {
            coEvery { locationHttpClient.fetchLocations() } throws Throwable("Failed to fetch locations")

            assertThatThrownBy {
                runBlocking { locationRepository.fetchLocations() }
            }.hasMessage("Failed to fetch locations")
        }
    }
}