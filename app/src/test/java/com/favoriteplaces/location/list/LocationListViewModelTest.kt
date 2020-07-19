package com.favoriteplaces.location.list

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.State
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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


    private fun getViewModel() =
        LocationListViewModel(LocationListViewInstruction(), locationInteractor)
}
