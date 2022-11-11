package com.favoriteplaces.location.detail

import com.favoriteplaces.location.data.LocationRepository
import com.favoriteplaces.location.list.LoadLocationsUseCase
import com.favoriteplaces.location.list.data.Location
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadLocationsTest {

    private lateinit var loadLocations: LoadLocationsUseCase

    @MockK internal lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loadLocations = LoadLocationsUseCase(repository)
    }

    @Test
    fun `returns success result with locations list when fetch locations was successful`() {
        runBlocking {
            val locationList = getLocationList()
            coEvery { repository.fetchLocations() } returns locationList

            val result = loadLocations()

            assertThat(result.isSuccess).isTrue()
            assertThat(result.getOrNull()).isEqualTo(locationList)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch locations was failed`() {
        runBlocking {
            val exception = Throwable("")
            coEvery { repository.fetchLocations() } throws exception

            val result = loadLocations()

            assertThat(result.isFailure).isTrue()
            assertThat(result.exceptionOrNull()).isEqualTo(exception)
        }
    }

    private fun getLocationList() = listOf(
        Location(0, "Some Place", 3.5, "Pub"),
        Location(1, "Some Other Place", 3.5, "Bar")
    )
}