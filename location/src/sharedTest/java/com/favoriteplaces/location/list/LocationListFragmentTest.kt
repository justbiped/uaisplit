package com.favoriteplaces.location.list

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.location.R
import com.favoriteplaces.location.list.data.remote.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.remote.LocationRemoteEntity
import com.favoriteplaces.location.list.ui.LocationAdapter
import com.favoriteplaces.location.list.ui.LocationListFragment
import com.hotmart.tests.instrumentation.FragmentScenario
import com.hotmart.tests.instrumentation.fragmentScenario
import com.hotmart.tests.instrumentation.rule.HttpResources
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions
import org.hamcrest.CoreMatchers.not
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LocationListFragmentTest {

    @get:Rule var hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<LocationListFragment>
    private val navHost = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        scenario = fragmentScenario()
        httpResources.clear()
        setScenarioNavGraph()
    }

    @Test
    fun show_progress_bar_on_load_locations() {
        httpResources.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(toJson())
                .setBodyDelay(1, TimeUnit.SECONDS)
        )

        onView(withId(R.id.locationListProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun show_loaded_locations_hiding_progress_bar() {
        httpResources.enqueue(MockResponse().setResponseCode(200).setBody(toJson()))

        onView(withText("Lugarzinho")).check(matches(isDisplayed()))
        onView(withId(R.id.locationListProgressBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun navigate_to_details_on_tap_on_a_location() {
        httpResources.enqueue(MockResponse().setResponseCode(200).setBody(toJson()))

        setScenarioNavGraph()

        onView(withId(R.id.locationRecyclerView))
            .perform(actionOnItemAtPosition<LocationAdapter.LocationViewHolder>(0, click()))

        Assertions.assertThat(navHost.currentDestination?.label).isEqualTo("LocationDetailsFragment")
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
        private val httpResources = HttpResources()

        @BeforeClass
        @JvmStatic
        fun beforeAll() {
            httpResources.init()
        }

        @AfterClass
        @JvmStatic
        fun afterAll() {
            httpResources.release()
        }
    }
}