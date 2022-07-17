package com.biped.locations.instrumentation

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import com.biped.locations.instrumentation.ApplicationStartScenario.Companion.LAUNCH_TIMEOUT
import com.biped.locations.instrumentation.ApplicationStartScenario.Companion.PACKAGE
import com.biped.test.instrumentation.runner.AutomatorRunner
import com.biped.test.instrumentation.runner.Step
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AutomatorRunner::class)
class LocationListScenario {
    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    @Step(order = 0, displayName = "Given that I am in the home screen")
    fun given_that_i_am_in_the_home_screen() {
        val homeMenu = device.wait(Until.findObject(By.text("Home")), LAUNCH_TIMEOUT)

        assert(homeMenu.isSelected)
    }

    @Test
    @Step(order = 1, displayName = "When the location is loaded")
    fun when_the_locations_is_loaded() {
        val isNotLoadingLocations =
            device.wait(Until.gone(By.res(PACKAGE, "locationListProgressBar")), LAUNCH_TIMEOUT)

        assert(isNotLoadingLocations)
    }

    @Test
    @Step(order = 2, displayName = "Then I see the list of locations")
    fun then_i_see_the_list_of_locations() {
        val locationList = UiScrollable(UiSelector().scrollable(true))

        assertThat(locationList.childCount).isGreaterThan(0)
    }
}