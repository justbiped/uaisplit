package com.favoriteplaces.location.data

import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.remote.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.remote.LocationRemoteEntity
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryTest {

    @MockK internal lateinit var locationHttpClient: LocationHttpClient

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


            try {
                runBlocking { locationRepository.fetchLocations() }
                fail("No exception was thrown by fetch locations")
            } catch (error: Throwable) {
                assertThat(error.message).isEqualTo("Failed to fetch locations")
            }
        }
    }
}