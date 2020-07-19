package com.favoriteplaces.location.list

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.Navigation
import com.downstairs.eatat.core.tools.State
import com.favoriteplaces.location.Location
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.location.list.data.LocationUIModel
import com.favoriteplaces.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @Mock
    lateinit var locationInteractor: LocationInteractor

    @Test
    fun `emits location list when fetch locations is successfully done`() = runBlocking {
        val observer = mock<Observer<List<LocationUIModel>>>()
        val locationList = listOf(Location(0, "Some Location", 4.5, "Pub"))
        whenever(locationInteractor.loadLocations()).thenReturn(Result.success(locationList))

        val viewModel = getViewModel()
        viewModel.locationList.observeForever(observer)

        verify(observer).onChanged(argThat {
            first() == getLocationUIModel()
        })
    }


    @Test
    fun `emits loading state when starts to fetch locations`() = runBlocking {
        val observer = mock<Observer<Instruction>>()

        val viewModel = getViewModel()
        viewModel.viewInstruction.observeForever(observer)

        verify(observer).onChanged(isA<State.Loading>())
    }

    @Test
    fun `emits success state when locations loading was successful`() = runBlocking {
        val observer = mock<Observer<Instruction>>()
        whenever(locationInteractor.loadLocations()).thenReturn(Result.success(emptyList()))

        val viewModel = getViewModel()
        viewModel.viewInstruction.observeForever(observer)

        verify(observer).onChanged(isA<State.Success>())
    }

    @Test
    fun `emits failed state when locations loading was failure`() = runBlocking {
        val observer = mock<Observer<Instruction>>()
        whenever(locationInteractor.loadLocations()).thenReturn(Result.failure(Exception("")))

        val viewModel = getViewModel()
        viewModel.viewInstruction.observeForever(observer)

        verify(observer).onChanged(isA<State.Failed>())
    }

    @Test
    fun `navigates to location details when a location was selected`() {
        runBlocking {
            val observer = mock<Observer<Instruction>>()
            val instruction = spy(LocationListViewInstruction())
            val locationUIModel = getLocationUIModel()
            whenever(locationInteractor.loadLocations()).thenReturn(Result.success(emptyList()))

            val viewModel = getViewModel(instruction)
            viewModel.viewInstruction.observeForever(observer)
            viewModel.onLocationSelected(locationUIModel)

            verify(instruction).navigateToLocationDetails(locationUIModel)
            verify(observer).onChanged(isA<Navigation>())
        }
    }

    private fun getViewModel(instruction: LocationListViewInstruction = LocationListViewInstruction()) =
        LocationListViewModel(instruction, locationInteractor)

    private fun getLocationUIModel(): LocationUIModel {
        return LocationUIModel(
            0,
            "Some Location",
            4.5,
            "Pub",
            "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?cs=srgb&dl=adult-beard-boy-casual-220453.jpg&fm=jpg"
        )
    }
}
