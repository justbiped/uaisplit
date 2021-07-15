package com.favoriteplaces.location.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.remote.LocationListRemoteEntity
import com.favoriteplaces.location.list.data.remote.LocationRemoteEntity
import com.favoriteplaces.tools.HttpResources
import com.favoriteplaces.tools.findView
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.CoreMatchers.not
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class LocationListFragmentTest {

    private val scenario =
        launchFragmentInContainer<LocationListFragment>(themeResId = R.style.AppTheme)

    @Before
    fun setUp() {
        val navHost = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.withFragment {
            navHost.setGraph(R.navigation.main_nav_graph)
            Navigation.setViewNavController(requireView(), navHost)
        }
    }

    @Test
    fun show_progress_bar_on_load_locations() {
        externalResources.mockHttpResponse(
            MockResponse()
                .setResponseCode(200)
                .setBody(toJson())
                .setBodyDelay(1, TimeUnit.SECONDS)
        )

        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.locationListProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun hide_progress_bar_after_show_loaded_locations() {
        externalResources.mockHttpResponse(MockResponse().setResponseCode(200).setBody(toJson()))

        scenario.moveToState(Lifecycle.State.RESUMED)

        findView(withText("Lugarzinho")).check(matches(isDisplayed()))
        onView(withId(R.id.locationListProgressBar)).check(matches(not(isDisplayed())))
    }

    private fun toJson(): String {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(LocationListRemoteEntity::class.java)
        return adapter.toJson(
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