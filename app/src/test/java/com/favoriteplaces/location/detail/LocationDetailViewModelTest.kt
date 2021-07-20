package com.favoriteplaces.location.detail

import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.State
import com.favoriteplaces.InstantTaskRule
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.location.detail.ui.LocationDetailInstruction
import com.favoriteplaces.location.detail.ui.LocationDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskRule()

    @MockK lateinit var getLocationDetails: GetLocationDetails

    private lateinit var viewModel: LocationDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LocationDetailViewModel(LocationDetailInstruction(), getLocationDetails)
    }

    @Test
    fun `emits location detail when fetch detail is successfully done`() = runBlocking {
        val observer = mockk<(LocationDetailUIModel) -> Unit>(relaxed = true)
        val locationDetail = getLocationDetail()
        coEvery { getLocationDetails(0) } returns Result.success(locationDetail)

        viewModel.locationDetail.observeForever(observer)
        viewModel.loadLocationDetails(0)

        verify {
            observer.invoke(withArg { detail ->
                assertThat(detail.name).isEqualTo("Some Location")
                assertThat(detail.type).isEqualTo("Some Type")
            })
        }
    }


    @Test
    fun `emits error state when fetch details fail`() = runBlocking {
        val observer = mockk<(Instruction) -> Unit>(relaxed = true)
        coEvery { getLocationDetails(0) } returns Result.failure(Throwable("Some error message"))

        viewModel.viewInstruction.observeForever(observer)
        viewModel.loadLocationDetails(0)

        verify {
            observer.invoke(withArg { instruction ->
                assertThat(instruction).isInstanceOf(State.Failed::class.java)
            })
        }
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
            Schedules(listOf(DaySchedule(Day.MONDAY, "10h", "11h")))
        )
}