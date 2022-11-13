package com.favoriteplaces.location.list

import com.biped.test.unit.CoroutineTestRule
import com.biped.test.unit.TestFlowSubject.Companion.assertThat
import com.biped.test.unit.mock
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

    @get:Rule val coroutineTestRule = CoroutineTestRule()

    private var loadLocations = mock<LoadLocationsUseCase>()
    private val instruction = spyk(LocationListInstructions())

    private lateinit var viewModel: LocationListViewModel

    @Before
    fun setUp() {
        viewModel = LocationListViewModel(instruction, loadLocations)
    }

    @Test
    fun `post default state on init`() = runTest {
        val testFlow = viewModel.instruction.test()

        assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Default::class.java)
        testFlow.finish()
    }

    @Test
    fun `post default, loading and success sequence on a successful locations load`() = runTest {
            val testFlow = viewModel.instruction.test()
            coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

            viewModel.fetchLocations()

            assertThat(testFlow).receivedExactlyEventsOf(
                Instruction.Default::class.java,
                Instruction.Loading::class.java,
                Instruction.Success::class.java
            )

            testFlow.finish()
        }

    @Test
    fun `post location list when fetch locations is successfully done`() = runTest {
        val locationList = listOf(locationUiFixture())
        val testFlow = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

        viewModel.fetchLocations()

        assertThat(testFlow).lastEvent().isEqualTo(Instruction.Success(locationList))
        testFlow.finish()
    }

    @Test
    fun `emit failed event when locations loading fail`() = runTest {
        val testFlow = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        assertThat(testFlow).receivedEventsOf(Instruction.Failure::class.java)
        testFlow.finish()
    }

    @Test
    fun `reset to initial state after an initial locations load`() = runTest {
        val testFlow = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Default::class.java)
        testFlow.finish()
    }

    @Test
    fun `emit default, loading, failure and default sequence on a loading fail`() = runTest {
        val testFlow = viewModel.instruction.test()
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        assertThat(testFlow).receivedExactlyEventsOf(
            Instruction.Default::class.java,
            Instruction.Loading::class.java,
            Instruction.Failure::class.java,
            Instruction.Default::class.java
        )
        testFlow.finish()
    }

    @Test
    fun `navigates to location details when a location was selected`() = runTest {
        val location = locationUiFixture()
        val expectedDestination = LocationListFragmentDirections.toLocationDetails(location.id)
        val testFlow = viewModel.instruction.test()

        viewModel.onLocationSelected(location)

        assertThat(testFlow).lastEvent().isEqualTo(Instruction.Navigation(expectedDestination))
        testFlow.finish()
    }
}
