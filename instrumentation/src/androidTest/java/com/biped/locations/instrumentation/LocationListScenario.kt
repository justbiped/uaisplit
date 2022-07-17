package com.biped.locations.instrumentation

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.biped.locations.instrumentation.ApplicationStartScenario.Companion.LAUNCH_TIMEOUT
import com.biped.locations.instrumentation.ApplicationStartScenario.Companion.PACKAGE
import com.biped.test.instrumentation.runner.AutomatorRunner
import com.biped.test.instrumentation.runner.Step
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
}