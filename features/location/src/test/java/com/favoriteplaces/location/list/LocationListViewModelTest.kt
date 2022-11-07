package com.favoriteplaces.location.list

import com.biped.test.unit.InstantTaskRule
import com.biped.test.unit.mock
import com.biped.test.unit.runFlowTest
import com.biped.test.unit.runTest
import com.biped.test.unit.test
import com.favoriteplaces.location.list.ui.Instruction
import com.favoriteplaces.location.list.ui.LocationListFragmentDirections
import com.favoriteplaces.location.list.ui.LocationListInstructions
import com.favoriteplaces.location.list.ui.LocationListViewModel
import com.favoriteplaces.location.locationFixture
import com.favoriteplaces.location.locationUiFixture
import io.mockk.coEvery
import io.mockk.spyk
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
    fun `post default state on init`() = runFlowTest {
        val flowTest = viewModel.instruction.test()

        flowTest.assertEvent().isInstanceOf<Instruction.Default>()
        flowTest
    }

    @Test
    fun `post default, loading and success sequence on a successful locations load`() =
        runFlowTest {
            val testFlow = viewModel.instruction.test()
            coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

            viewModel.fetchLocations()

            testFlow.assertEvents().hasExactlyElementsOfTypes(
                Instruction.Default::class.java,
                Instruction.Loading::class.java,
                Instruction.Success::class.java
            )
            testFlow
        }

    @Test
    fun `post location list when fetch locations is successfully done`() = runFlowTest {
        val locationList = listOf(locationUiFixture())
        val flowTest = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

        viewModel.fetchLocations()

        flowTest.assertEvent()
            .isInstanceOf<Instruction.Success>()
            .hasFieldOrPropertyWithValue("locations", locationList)
        flowTest
    }

    @Test
    fun `emit failed event when locations loading fail`() = runFlowTest {
        val flowTest = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        flowTest.assertEvents().hasAtLeastOneElementOfType(
            Instruction.Failure::class.java
        )
        flowTest
    }

    @Test
    fun `reset to initial state after an initial locations load`() = runFlowTest {
        val flowTest = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        flowTest.assertEvent().isInstanceOf<Instruction.Default>()
        flowTest
    }

    @Test
    fun `emit default, loading, failure and default sequence on a loading fail`() = runFlowTest {
        val flowTest = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        flowTest.assertEvents().hasExactlyElementsOfTypes(
            Instruction.Default::class.java,
            Instruction.Loading::class.java,
            Instruction.Failure::class.java,
            Instruction.Default::class.java
        )
        flowTest
    }

    @Test
    fun `navigates to location details when a location was selected`() = runFlowTest {
        val location = locationUiFixture()
        val expectedDestination = LocationListFragmentDirections.toLocationDetails(location.id)
        val testFlow = viewModel.instruction.test()

        viewModel.onLocationSelected(location)

        testFlow.assertEvent()
            .isInstanceOf<Instruction.Navigation>()
            .isEqualTo(Instruction.Navigation(expectedDestination))

        testFlow
    }
}
