package com.favoriteplaces.location.list

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.remote.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.remote.LocationRemoteEntity
import com.favoriteplaces.location.list.ui.LocationAdapter
import com.favoriteplaces.location.list.ui.LocationListFragment
import com.favoriteplaces.tools.HttpResources
import com.favoriteplaces.tools.findView
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers.not
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class LocationListFragmentTest {

    private lateinit var scenario: FragmentScenario<LocationListFragment>
    private val navHost = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
        setScenarioNavGraph()
    }

    @Test
    fun show_progress_bar_on_load_locations() {
        externalResources.mockHttpResponse(
            MockResponse()
                .setResponseCode(200)
                .setBody(toJson())
                .setBodyDelay(1, TimeUnit.SECONDS)
        )

        scenario.recreate()

        onView(withId(R.id.locationListProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun show_loaded_locations_hiding_progress_bar() {
        externalResources.mockHttpResponse(MockResponse().setResponseCode(200).setBody(toJson()))

        findView(withText("Lugarzinho")).check(matches(isDisplayed()))
        onView(withId(R.id.locationListProgressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun navigate_to_details_on_tap_on_a_location() {
        externalResources.mockHttpResponse(MockResponse().setResponseCode(200).setBody(toJson()))

        scenario.recreate()
        setScenarioNavGraph()

        onView(withId(R.id.locationRecyclerView))
            .perform(actionOnItemAtPosition<LocationAdapter.LocationViewHolder>(0, click()))

        assertThat(navHost.currentDestination?.label).isEqualTo("LocationDetailsFragment")
    }

    private fun setScenarioNavGraph() {
        scenario.withFragment {
            navHost.setGraph(R.navigation.location_nav_graph)
            Navigation.setViewNavController(requireView(), navHost)
        }
    }

    private fun toJson(): String {
        return Json.encodeToString(
            LocationListRemoteEntity(
                listOf(LocationRemoteEntity(1, "Lugarzinho", 4.3, "Pub"))
            )
        )
    }

    companion object {
        private val externalResources = HttpResources()

        @BeforeClass
        @JvmStatic
        fun beforeAll() {
            externalResources.init()
        }

        @AfterClass
        @JvmStatic
        fun afterAll() {
            externalResources.release()
        }
    }
}