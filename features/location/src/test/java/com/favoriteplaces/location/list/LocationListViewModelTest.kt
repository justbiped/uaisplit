package com.favoriteplaces.location.list

import biped.works.coroutiens.test.CoroutineTestRule
import biped.works.coroutiens.test.TestFlowSubject.Companion.assertThat
import com.biped.test.unit.mock
import biped.works.coroutiens.test.runTest
import biped.works.coroutiens.test.testFlowOf
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

    @get:Rule val coroutineTestRule = biped.works.coroutiens.test.CoroutineTestRule()

    private var loadLocations = mock<LoadLocationsUseCase>()
    private val instruction = spyk(LocationListInstructions())

    private lateinit var viewModel: LocationListViewModel

    @Before
    fun setUp() {
        viewModel = LocationListViewModel(instruction, loadLocations)
    }

    @Test
    fun `post default state on init`() = biped.works.coroutiens.test.runTest {
        val testFlow = testFlowOf(viewModel.instruction)

        assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Default::class.java)
        testFlow.finish()
    }

    @Test
    fun `post default, loading and success sequence on a successful locations load`() =
        biped.works.coroutiens.test.runTest {
            val testFlow = testFlowOf(viewModel.instruction)
            coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

            viewModel.fetchLocations()

            assertThat(testFlow).hasCollectedExactlyInstanceOf(
                Instruction.Default::class.java,
                Instruction.Loading::class.java,
                Instruction.Success::class.java
            )

            testFlow.finish()
        }

    @Test
    fun `post location list when fetch locations is successfully done`() =
        biped.works.coroutiens.test.runTest {
            val locationList = listOf(locationUiFixture())
            val testFlow = testFlowOf(viewModel.instruction)
            coEvery { loadLocations() } returns Result.success(listOf(locationFixture()))

            viewModel.fetchLocations()

            assertThat(testFlow).lastEvent().isEqualTo(Instruction.Success(locationList))
            testFlow.finish()
        }

    @Test
    fun `emit failed event when locations loading fail`() = biped.works.coroutiens.test.runTest {
        val testFlow = testFlowOf(viewModel.instruction)
        coEvery { loadLocations() } returns Result.failure(Exception(""))

        viewModel.fetchLocations()

        assertThat(testFlow).hasCollectedInstanceOf(Instruction.Failure::class.java)
        testFlow.finish()
    }

    @Test
    fun `reset to initial state after an initial locations load`() =
        biped.works.coroutiens.test.runTest {
            val testFlow = testFlowOf(viewModel.instruction)
            coEvery { loadLocations() } returns Result.failure(Exception(""))

            viewModel.fetchLocations()

            assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Default::class.java)
            testFlow.finish()
        }

    @Test
    fun `emit default, loading, failure and default sequence on a loading fail`() =
        biped.works.coroutiens.test.runTest {
            val testFlow = testFlowOf(viewModel.instruction)
            coEvery { loadLocations() } returns Result.failure(Exception(""))

            viewModel.fetchLocations()

            assertThat(testFlow).hasCollectedExactlyInstanceOf(
                Instruction.Default::class.java,
                Instruction.Loading::class.java,
                Instruction.Failure::class.java,
                Instruction.Default::class.java
            )
            testFlow.finish()
        }

    @Test
    fun `navigates to location details when a location was selected`() =
        biped.works.coroutiens.test.runTest {
            val location = locationUiFixture()
            val expectedDestination = LocationListFragmentDirections.toLocationDetails(location.id)
            val testFlow = testFlowOf(viewModel.instruction)

            viewModel.onLocationSelected(location)

            assertThat(testFlow).lastEvent().isEqualTo(Instruction.Navigation(expectedDestination))
            testFlow.finish()
        }
}
