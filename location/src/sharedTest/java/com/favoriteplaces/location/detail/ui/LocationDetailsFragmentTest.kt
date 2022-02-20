package com.favoriteplaces.location.detail.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.location.R
import com.favoriteplaces.location.detail.data.remote.LocationDetailRemoteEntity
import com.favoriteplaces.location.locationDetailsApiFixture
import com.hotmart.tests.instrumentation.FragmentScenario
import com.hotmart.tests.instrumentation.fragmentScenario
import com.hotmart.tests.tools.HttpResources
import com.hotmart.tests.tools.NestedScrollToAction.Companion.nestedScrollTo
import com.hotmart.tests.tools.hasText
import com.hotmart.tests.tools.isVisible
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
    @get:Rule var hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: FragmentScenario<LocationDetailsFragment>

    @Before
    fun setUp() {
        scenario = fragmentScenario()
    }

    @Test
    fun show_location_details_on_success_details_fetch() {
        val fixture = locationDetailsApiFixture()
        httpResources.enqueue(createSuccessLocationResponse(fixture))

        onView(withId(R.id.locationDetailNameText)).check(hasText(fixture.name))
        onView(withId(R.id.locationDetailsRatingBar)).check(isVisible())
        onView(withId(R.id.locationDetailRatingText)).check(hasText("${fixture.review}"))
        onView(withId(R.id.locationDetailAboutText)).check(hasText(fixture.about))
        onView(withId(R.id.locationDetailSchedule)).check(isVisible())
        onView(withId(R.id.locationDetailPhoneText)).check(hasText(fixture.phone))
        onView(withId(R.id.locationDetailAddressText)).check(hasText(fixture.address))
        onView(withId(R.id.seeMoreCommentsText)).perform(nestedScrollTo()).check(isVisible())
    }

    private fun createSuccessLocationResponse(locationDetails: LocationDetailRemoteEntity): MockResponse {
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