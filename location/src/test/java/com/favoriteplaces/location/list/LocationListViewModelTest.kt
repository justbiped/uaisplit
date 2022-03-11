package com.favoriteplaces.location.list

import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.ui.LocationImageUIModel
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import com.favoriteplaces.location.list.ui.Instruction
import com.favoriteplaces.location.list.ui.LocationListInstructions
import com.favoriteplaces.location.list.ui.LocationListViewModel
import com.favoriteplaces.tests.InstantTaskRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @MockK(relaxed = true)
    lateinit var loadLocations: LoadLocations

    private val instruction = spyk(LocationListInstructions())

    private lateinit var viewModel: LocationListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LocationListViewModel(instruction, loadLocations)
    }

    @Test
    fun `emits location list when fetch locations is successfully done`() = runBlocking {
        val observer = mockk<(List<LocationUIModel>) -> Unit>(relaxed = true)
        val locationList = listOf(Location(0, "Some Location", 4.5, "Pub"))
        coEvery { loadLocations() } returns Result.success(locationList)

        viewModel.locationList.observeForever(observer)
        viewModel.fetchLocations()

        verify {
            observer.invoke(withArg { locations ->
                assertThat(locations.first().name).isEqualTo("Some Location")
                assertThat(locations.first().type).isEqualTo("Pub")

            })
        }
    }


    @Test
    fun `emits loading state when starts to fetch locations`() = runBlocking {
        val observer = mockk<(Instruction) -> Unit>(relaxed = true)
        coEvery { loadLocations() } returns Result.success(emptyList())

        viewModel.instruction.observeForever(observer)
        viewModel.fetchLocations()

        verify {
            observer.invoke(withArg { instruction ->
                assertThat(instruction).isInstanceOf(Instruction.Loading::class.java)
            })
        }
    }

    @Test
    fun `emits success state when locations loading was successful`() = runBlocking {
        val observer = mockk<(Instruction) -> Unit>(relaxed = true)
        coEvery { loadLocations() } returns Result.success(emptyList())

        viewModel.instruction.observeForever(observer)
        viewModel.fetchLocations()

        verify {
            observer.invoke(withArg { instruction ->
                assertThat(instruction).isInstanceOf(Instruction.Success::class.java)
            })
        }
    }

    @Test
    fun `emits failed state when locations loading was failure`() = runBlocking {
        val observer = mockk<(Instruction) -> Unit>(relaxed = true)
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.instruction.observeForever(observer)
        viewModel.fetchLocations()

        verify {
            observer.invoke(withArg { instruction ->
                assertThat(instruction).isInstanceOf(Instruction.Failure::class.java)
            })
        }
    }

    @Test
    fun `navigates to location details when a location was selected`() {
        runBlocking {
            val observer = mockk<(Instruction) -> Unit>(relaxed = true)
            val locationUIModel = getLocationUIModel()

            viewModel.instruction.observeForever(observer)
            viewModel.onLocationSelected(locationUIModel)

            verify { instruction.navigateToLocationDetails(locationUIModel) }
            verify {
                observer.invoke(withArg { instruction ->
                    assertThat(instruction).isInstanceOf(Instruction.Navigation::class.java)
                })
            }
        }
    }

    private fun getLocationUIModel(): LocationUIModel {
        return LocationUIModel(
            0,
            "Some Location",
            4.5,
            "Pub",
            LocationImageUIModel(-1, "")
        )
    }
}
