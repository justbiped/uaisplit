package com.favoriteplaces.location.detail

import com.biped.test.InstantTaskRule
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedule
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.location.detail.ui.Instruction
import com.favoriteplaces.location.detail.ui.LocationDetailInstructions
import com.favoriteplaces.location.detail.ui.LocationDetailViewModel
import com.favoriteplaces.location.detail.ui.ScheduleFormatter
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
class LocationDetailViewModelTest {

    @get:Rule val instantTaskRule = InstantTaskRule()
    @MockK internal lateinit var getLocationDetails: GetLocationDetails
    @MockK internal lateinit var scheduleFormatter: ScheduleFormatter

    private lateinit var viewModel: LocationDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LocationDetailViewModel(
            LocationDetailInstructions(),
            getLocationDetails,
            scheduleFormatter
        )
    }

    @Test
    fun `emits location detail when fetch detail is successfully done`() = runBlocking {
        val observer = mockk<(LocationDetailUIModel) -> Unit>(relaxed = true)
        val locationDetail = getLocationDetail()

        every { scheduleFormatter.format(any()) } returns "Mon to Sat: 10h at 19h"
        coEvery { getLocationDetails(0) } returns Result.success(locationDetail)

      //  viewModel.locationDetail.observeForever(observer)
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

      //  viewModel.viewInstruction.observeForever(observer)
        viewModel.loadLocationDetails(0)

        verify {
            observer.invoke(withArg { instruction ->
                assertThat(instruction).isInstanceOf(Instruction.Failure::class.java)
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
            Schedule(listOf(DaySchedule(Day.MONDAY, "10h", "11h")))
        )
}