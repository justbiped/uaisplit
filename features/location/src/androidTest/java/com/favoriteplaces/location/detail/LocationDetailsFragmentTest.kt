package com.favoriteplaces.location.detail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class LocationDetailsFragmentTest {

    @get:Rule var hiltRule = HiltAndroidRule(this)
    private lateinit var scenario: FragmentScenario<LocationDetailsFragment>

    @Test
    fun show_location_details_on_success_details_fetch() {
        val detail = locationDetailsApiFixture()
        httpResources.enqueue(successLocationResponse(detail))

        scenario = fragmentScenario()

        onView(withId(R.id.locationRatingBar)).check(isVisible())
        onView(withText("${detail.review}")).check(isVisible())
        onView(withId(R.id.locationDetailAboutText)).check(hasText(detail.about))
        onView(withId(R.id.locationDetailSchedule)).perform(nestedScrollTo()).check(isVisible())
        onView(withId(R.id.locationDetailPhoneText)).perform(nestedScrollTo()).check(hasText(detail.phone))
        onView(withId(R.id.locationDetailAddressText)).perform(nestedScrollTo()).check(hasText(detail.address))
        onView(withId(R.id.locationReviewList)).perform(nestedScrollTo()).check(isVisible())
        onView(withId(R.id.seeMoreCommentsText)).perform(nestedScrollTo()).check(isVisible())
    }

    private fun successLocationResponse(locationDetails: LocationDetailApiModel): MockResponse {
        val body = Json.encodeToString(locationDetails)
        return MockResponse()
            .setResponseCode(200)
            .setBody(body)
    }

    companion object{
        @ClassRule @JvmField var httpResources = HttpResources()
    }
}