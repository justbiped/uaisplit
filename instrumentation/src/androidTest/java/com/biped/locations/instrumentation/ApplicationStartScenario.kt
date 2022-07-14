package com.biped.locations.instrumentation

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.favoriteplaces.core.test.instrumentation.runner.AutomatorRunner
import com.favoriteplaces.core.test.instrumentation.runner.Step
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AutomatorRunner::class)
class ApplicationStartScenario {

    private val device = UiDevice.getInstance(getInstrumentation())
    private val context = getApplicationContext<Context>()

    @Test
    @Step("Given i have the location app installed", order = 0)
    fun given_i_have_the_sparkle_installed() {
        val launcherPackage = device.launcherPackageName
        assertThat(launcherPackage).isNotNull()
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)

        val packageInfo = context.packageManager.getPackageInfo(PACKAGE, 0)

        assertThat(packageInfo).isNotNull()
    }

    @Test
    @Step("When i launch it", order = 1)
    fun when_i_launch_it() {
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.res(PACKAGE, "mainFragmentContainer")), LAUNCH_TIMEOUT)
    }

    @Test
    @Step("Then i see the home screen", order = 2)
    fun then_i_see_the_home_screen_with_loaded_locations() {
        val isHomeVisible = device.wait(
            Until.hasObject(By.scrollable(true)),
            LAUNCH_TIMEOUT
        )

        assert(isHomeVisible)
    }

    companion object {
        const val LAUNCH_TIMEOUT = 3000L
        private const val PACKAGE = "com.biped.locations"
    }
}