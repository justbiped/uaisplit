package com.hotmart.test

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@Suppress("ClassName", "TestFunctionName")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class See_list_of_locations_when_launch_application {

    private val device = UiDevice.getInstance(getInstrumentation())
    private val context = getApplicationContext<Context>()

    @Test
    fun _0__Given_i_have_the_sparkle_installed() {
        val launcherPackage: String = getLauncherPackageName()
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun _1__When_i_launch_it() {
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.res(PACKAGE, "mainFragmentContainer")), LAUNCH_TIMEOUT)
    }

    @Test
    fun _2__Then_i_see_the_home_screen_with_loaded_locations() {
        val locationListView = UiScrollable(UiSelector().scrollable(true))
        assertThat(locationListView.childCount).isGreaterThan(0)
    }

    private fun getLauncherPackageName(): String {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        val resolveInfo = getApplicationContext<Context>()
            .packageManager
            .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolveInfo?.activityInfo?.packageName ?: throw Throwable("Unable to start $PACKAGE")
    }

    companion object {
        private const val LAUNCH_TIMEOUT = 3000L
        private const val PACKAGE = "com.favoriteplaces"
    }
}