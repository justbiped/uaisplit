package com.favoriteplaces.location.detail

import androidx.lifecycle.Observer
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.State
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedule
import com.favoriteplaces.location.detail.data.domain.Schedules
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.core.tools.InstantTaskRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @Mock
    lateinit var locationInteractor: LocationInteractor

    private lateinit var viewModel: LocationDetailViewModel

    @Before
    fun setUp() {
        viewModel = LocationDetailViewModel(LocationDetailInstruction(), locationInteractor)
    }

    @Test
    fun `emits location detail when fetch detail is successfully done`() = runBlocking {
        val observer = mock<Observer<LocationDetailUIModel>>()
        val locationDetail = getLocationDetail()
        whenever(locationInteractor.loadLocationDetails(0)).thenReturn(Result.success(locationDetail))

        viewModel.locationDetail.observeForever(observer)
        viewModel.loadLocationDetails(0)

        verify(observer).onChanged(argThat {
            name == "Some Location" && type == "Some Type"
        })
    }


    @Test
    fun `emits error state when fetch details fail`() = runBlocking {
        val observer = mock<Observer<Instruction>>()
        whenever(locationInteractor.loadLocationDetails(0)).thenReturn(Result.failure(Throwable("Some error message")))

        viewModel.viewInstruction.observeForever(observer)
        viewModel.loadLocationDetails(0)

        verify(observer).onChanged(isA<State.Failed>())
    }


    private fun getLocationDetail() =
        LocationDetail(
            0,
            "Some Location",
            4.0,
            "Some Type",
            "something about",
            "314142",
            "some address",
            Schedules(listOf(Schedule(Day.MONDAY, "10h", "11h")))
        )
}