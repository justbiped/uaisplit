package com.favoriteplaces.location.detail.ui

import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.favoriteplaces.location.locationDetailsApiFixture
import com.hotmart.tests.instrumentation.FragmentScenario
import com.hotmart.tests.instrumentation.fragmentScenario
import com.hotmart.tests.tools.HttpResources
import com.hotmart.tests.tools.isVisible
import com.hotmart.tests.tools.waitView
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
        httpResources.enqueue(createSuccessLocationDetailsResponse())

        waitView(withText("Café da Japa")).check(isVisible())
    }

    private fun createSuccessLocationDetailsResponse(): MockResponse {
        val body = Json.encodeToString(locationDetailsApiFixture())

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