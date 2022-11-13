package com.favoriteplaces.location.detail

import com.biped.test.unit.CoroutineTestRule
import com.biped.test.unit.TestFlowSubject.Companion.assertThat
import com.biped.test.unit.runTest
import com.biped.test.unit.test
import com.favoriteplaces.location.detail.data.domain.Day
import com.favoriteplaces.location.detail.data.domain.DaySchedule
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.domain.Schedule
import com.favoriteplaces.location.detail.ui.Instruction
import com.favoriteplaces.location.detail.ui.LocationDetailInstructions
import com.favoriteplaces.location.detail.ui.LocationDetailViewModel
import com.favoriteplaces.location.detail.ui.ScheduleFormatter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationDetailViewModelTest {

    @get:Rule val coroutineTestRule = CoroutineTestRule()
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
    fun `emits location detail when fetch detail is successfully done`() = runTest {
        val testFlow = viewModel.viewInstruction.test()
        val locationDetail = getLocationDetail()

        every { scheduleFormatter.format(any()) } returns "Mon to Sat: 10h at 19h"
        coEvery { getLocationDetails(0) } returns Result.success(locationDetail)

        viewModel.loadLocationDetails(0)

        assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Success::class.java)

        testFlow.finish()
    }

    @Test
    fun `emits error state when fetch details fail`() = runBlocking {
        val testFlow = viewModel.viewInstruction.test()
        coEvery { getLocationDetails(0) } returns Result.failure(Throwable("Some error message"))

        viewModel.loadLocationDetails(0)

        assertThat(testFlow).lastEvent().isInstanceOf(Instruction.Failure::class.java)
        testFlow.finish()
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
