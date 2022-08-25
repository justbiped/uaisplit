package com.favoriteplaces.location.list

import com.biped.test.unit.InstantTaskRule
import com.biped.test.unit.flowTest
import com.biped.test.unit.mock
import com.favoriteplaces.location.list.ui.Instruction
import com.favoriteplaces.location.list.ui.LocationListInstructions
import com.favoriteplaces.location.list.ui.LocationListViewModel
import com.favoriteplaces.location.locationFixture
import com.favoriteplaces.location.locationUiFixture
import io.mockk.coEvery
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class LocationListViewModelTest {

    @get:Rule val instantTaskRule = InstantTaskRule()

    private var loadLocations = mock<LoadLocationsUseCase>()
    private val instruction = spyk(LocationListInstructions())

    private lateinit var viewModel: LocationListViewModel

    @Before
    fun setUp() {
        viewModel = LocationListViewModel(instruction, loadLocations)
    }

    @Test
    fun `post loading state when starts to fetch locations`() = flowTest {
        val instructionEvents = mock<MutableList<Instruction>>()
        val collectJob = launch { viewModel.instruction.toList(instructionEvents) }
        coEvery { loadLocations() } returns Result.success(emptyList())

        viewModel.fetchLocations()

        verify(exactly = 1) {
            instructionEvents.add(withArg { assert(it is Instruction.Loading) })
        }

        collectJob
    }

    @Test
    fun `post success state when locations loading was successful`() = flowTest {
        val instructionEvents = mock<MutableList<Instruction>>()
        val collectJob = launch { viewModel.instruction.toList(instructionEvents) }
        coEvery { loadLocations() } returns Result.success(emptyList())

        viewModel.fetchLocations()

        verify(exactly = 1) {
            instructionEvents.add(withArg { assert(it is Instruction.Success) })
        }

        collectJob
    }

    @Test
    fun `post location list when fetch locations is successfully done`() = flowTest {
        val instructionEvents = mock<MutableList<Instruction>>()
        val collectJob = launch { viewModel.instruction.toList(instructionEvents) }
        coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

        viewModel.fetchLocations()

        verify(exactly = 1) {
            instructionEvents.add(withArg { instruction ->
                assertThat((instruction as? Instruction.Success)?.locations)
                    .first()
                    .hasFieldOrPropertyWithValue("name", "Some Location")
            })
        }

        collectJob
    }


    @Test
    fun `emits failed state when locations loading was failure`() = flowTest {
        val instructionEvents = mock<MutableList<Instruction>>()
        val collectJob = launch { viewModel.instruction.toList(instructionEvents) }
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        verify {
            instructionEvents.add(withArg { assert(it is Instruction.Failure) })
        }

        collectJob
    }

    @Test
    fun `navigates to location details when a location was selected`() = flowTest {
        val locationUiModel = locationUiFixture()
        val instructionEvents = mock<MutableList<Instruction>>()
        val collectJob = launch { viewModel.instruction.toList(instructionEvents) }

        viewModel.onLocationSelected(locationUiModel)

        verify { instruction.navigateToLocationDetails(locationUiModel) }
        verify {
            instructionEvents.add(withArg { instruction ->
                assert(instruction is Instruction.Navigation)
            })
        }

        collectJob
    }
}
