package com.favoriteplaces.location.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.R
import com.favoriteplaces.core.test.instrumentation.FragmentScenario
import com.favoriteplaces.core.test.instrumentation.action.NestedScrollToAction.Companion.nestedScrollTo
import com.favoriteplaces.core.test.instrumentation.action.hasText
import com.favoriteplaces.core.test.instrumentation.action.isVisible
import com.favoriteplaces.core.test.instrumentation.fragmentScenario
import com.favoriteplaces.core.test.instrumentation.rule.HttpResources
import com.favoriteplaces.location.detail.ui.LocationDetailsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.*
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LocationDetailsFragmentTest {
    @get:Rule var hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<LocationDetailsFragment>

    @Before
    fun setUp() {
        scenario = fragmentScenario()
    }

    @Test
    fun show_location_details_on_success_details_fetch() {
//        val fixture = locationDetailsApiFixtureJson(about = "Chavoso", phone = "123", address = "rua dos bobos")
//        httpResources.enqueue(successLocationResponse(fixture))
//
//        onView(withId(R.id.locationDetailNameText)).check(hasText("Café da Japa"))
//        onView(withId(R.id.locationDetailsRatingBar)).check(isVisible())
//        onView(withId(R.id.locationDetailRatingText)).check(hasText("5.0"))
//        onView(withId(R.id.locationDetailAboutText)).check(hasText("Chavoso"))
//        onView(withId(R.id.locationDetailSchedule)).perform(nestedScrollTo()).check(isVisible())
//        onView(withId(R.id.locationDetailPhoneText)).perform(nestedScrollTo()).check(hasText("123"))
//        onView(withId(R.id.locationDetailAddressText)).perform(nestedScrollTo()).check(hasText("rua dos bobos"))
//        onView(withId(R.id.locationReviewList)).perform(nestedScrollTo()).check(isVisible())
//        onView(withId(R.id.seeMoreCommentsText)).perform(nestedScrollTo()).check(isVisible())
    }

    private fun successLocationResponse(body: String): MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(body)
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