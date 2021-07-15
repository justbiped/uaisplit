package com.favoriteplaces.location.detail

import com.favoriteplaces.location.LocationRepository
import com.favoriteplaces.location.list.LoadLocations
import com.favoriteplaces.location.list.data.Location
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadLocationsTest {

    private lateinit var loadLocations: LoadLocations

    @MockK
    lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loadLocations = LoadLocations(repository)
    }

    @Test
    fun `returns success result with locations list when fetch locations was successful`() {
        runBlocking {
            val locationList = getLocationList()
            coEvery { repository.fetchLocations() } returns locationList

            val result = loadLocations()

            Assertions.assertThat(result.isSuccess).isTrue()
            Assertions.assertThat(result.getOrNull()).isEqualTo(locationList)
        }
    }

    @Test
    fun `returns failure result with launched exception when fetch locations was failed`() {
        runBlocking {
            val exception = Throwable("")
            coEvery { repository.fetchLocations() } throws exception

            val result = loadLocations()

            Assertions.assertThat(result.isFailure).isTrue()
            Assertions.assertThat(result.exceptionOrNull()).isEqualTo(exception)
        }
    }

    private fun getLocationList() = listOf(
        Location(0, "Some Place", 3.5, "Pub"),
        Location(1, "Some Other Place", 3.5, "Bar")
    )
}