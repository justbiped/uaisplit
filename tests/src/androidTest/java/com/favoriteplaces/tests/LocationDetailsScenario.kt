package com.favoriteplaces.tests

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By.text
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import com.favoriteplaces.tests.LocationListScenario.Companion.LAUNCH_TIMEOUT
import com.favoriteplaces.tests.instrumentation.runner.AutomatorRunner
import com.favoriteplaces.tests.instrumentation.runner.Step
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AutomatorRunner::class)
class LocationDetailsScenario {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    @Step("Given i have listed locations", 0)
    fun given_i_have_listed_locations() {
        UiScrollable(UiSelector().scrollable(true)).getChild(UiSelector().text("Hangar")).click()
    }

    @Test
    @Step("When i scroll to screen bottom", 1)
    fun when_i_scroll_to_screen_bottom() {
        UiScrollable(UiSelector().scrollable(true)).scrollToEnd(2)
        UiScrollable(UiSelector().scrollable(true)).scrollToEnd(2)
    }

    @Test
    @Step("Then i see the click to see more comments button", 2)
    fun then_i_see_the_click_to_see_more_comments_button() {
        val isClickToSeeCommentsVisible =
            device.wait(Until.hasObject(text("Click here to see all reviews")), LAUNCH_TIMEOUT)

        assert(isClickToSeeCommentsVisible)
    }
}