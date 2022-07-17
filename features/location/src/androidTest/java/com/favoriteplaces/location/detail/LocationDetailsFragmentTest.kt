package com.favoriteplaces.location.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.biped.test.instrumentation.FragmentScenario
import com.biped.test.instrumentation.action.NestedScrollToAction.Companion.nestedScrollTo
import com.biped.test.instrumentation.action.hasText
import com.biped.test.instrumentation.action.isVisible
import com.biped.test.instrumentation.fragmentScenario
import com.biped.test.instrumentation.rule.HttpResources
import com.favoriteplaces.location.R
import com.favoriteplaces.location.detail.data.remote.LocationDetailApiModel
import com.favoriteplaces.location.detail.ui.LocationDetailsFragment
import com.favoriteplaces.location.locationDetailsApiFixture
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.MockResponse
import org.junit.*
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LocationDetailsFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<LocationDetailsFragment>

    @Before
    fun setUp() {
        scenario = fragmentScenario()
    }

    @Test
    fun show_location_details_on_success_details_fetch() {
        val fixture = locationDetailsApiFixture()
        httpResources.enqueue(successLocationResponse(fixture))

        onView(withId(R.id.locationDetailNameText)).check(hasText(fixture.name))
        onView(withId(R.id.locationDetailsRatingBar)).check(isVisible())
        onView(withId(R.id.locationDetailRatingText)).check(hasText("${fixture.review}"))
        onView(withId(R.id.locationDetailAboutText)).check(hasText(fixture.about))
        onView(withId(R.id.locationDetailSchedule)).perform(nestedScrollTo()).check(isVisible())
        onView(withId(R.id.locationDetailPhoneText)).perform(nestedScrollTo())
            .check(hasText(fixture.phone))
        onView(withId(R.id.locationDetailAddressText)).perform(nestedScrollTo())
            .check(hasText(fixture.address))
        onView(withId(R.id.locationReviewList)).perform(nestedScrollTo()).check(isVisible())
        onView(withId(R.id.seeMoreCommentsText)).perform(nestedScrollTo()).check(isVisible())
    }

    private fun successLocationResponse(locationDetails: LocationDetailApiModel): MockResponse {
        val body = Json.encodeToString(locationDetails)
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