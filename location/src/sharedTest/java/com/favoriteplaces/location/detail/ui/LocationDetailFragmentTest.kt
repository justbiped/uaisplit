package com.favoriteplaces.location.detail.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.location.R
import com.favoriteplaces.location.detail.data.remote.LocationDetailRemoteEntity
import com.favoriteplaces.location.detail.data.remote.ScheduleRemoteEntity
import com.favoriteplaces.location.detail.data.remote.SchedulesRemoteEntity
import com.hotmart.coretests.tools.HttpResources
import com.hotmart.coretests.tools.findView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationDetailFragmentTest {

    @get:Rule val httpResources = HttpResources()

    private lateinit var scenario: FragmentScenario<LocationDetailFragment>
    private val navHost = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        scenario =
            launchFragmentInContainer(
                themeResId = R.style.AppTheme,
                fragmentArgs = bundleOf("locationId" to 0)
            )
        setScenarioNavGraph()
    }

    @Test
    fun show_more_comments_button_on_bottom_of_screen() {
        httpResources.enqueue(stubSuccessDetailResponse())

        findView(withText(R.string.more_reviews_click_msg))
            .perform(swipeUp())
            .check(matches(isDisplayed()))
    }

    private fun setScenarioNavGraph() {
        scenario.withFragment {
            navHost.setGraph(R.navigation.location_nav_graph)
            Navigation.setViewNavController(requireView(), navHost)
        }
    }

    private fun stubSuccessDetailResponse(): MockResponse {
        val body = Json.encodeToString(locationDetailRemoteFixture())
        return MockResponse()
            .setResponseCode(200)
            .setBody(body)
    }
}


fun locationDetailRemoteFixture(
    id: Int = 0,
    name: String = "Cafezinho da Japa",
    review: Double = 10.0,
    type: String = "Cafe",
    about: String = "O café mais chavoso do brasil",
    phone: String = "9995467",
    address: String = "Barrerão",
    schedule: SchedulesRemoteEntity = SchedulesRemoteEntity(
        wednesday = ScheduleRemoteEntity(
            "10h",
            "14h"
        )
    )
) = LocationDetailRemoteEntity(id, name, review, type, about, phone, address, schedule)
