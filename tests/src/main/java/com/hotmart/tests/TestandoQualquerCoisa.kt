package com.hotmart.tests

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestandoQualquerCoisa {

    private lateinit var device: UiDevice

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = getLauncherPackageName()
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)

        // Launch the blueprint app
        val context: Context = getApplicationContext()
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(PACKAGE)

        // Clear out any previous instances
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun checkPreconditions() {
        seeLocationList()
        selectPadariaPelicano()
        seePadariaPalermoDetails()
        scrollToTheCommendSession()
        seeThreeComments()
    }

    private fun seeThreeComments() {
        val reviewList = UiScrollable(
            UiSelector().resourceId("com.favoriteplaces:id/locationReviewList")
        )
        assertThat(reviewList.childCount).isEqualTo(3)

        val reviewTitle =
            reviewList.getChild(UiSelector().index(1).className(ViewGroup::class.java))
                .getChild(UiSelector().resourceId("com.favoriteplaces:id/locationReviewTitleText"))
        assertThat(reviewTitle.text).isEqualTo("Café da manhã delicioso")
    }

    private fun scrollToTheCommendSession() {
        UiScrollable(UiSelector().scrollable(true)).flingToEnd(2)
    }

    private fun seePadariaPalermoDetails() {
        val title = device.findObject(By.res(PACKAGE, "locationDetailNameText"))
        assertThat(title.text).isEqualTo("Padaria Pelicano")
    }

    private fun selectPadariaPelicano() {
        UiScrollable(UiSelector().scrollable(true))
            .getChildByText(UiSelector().text("Padaria Pelicano"), "Padaria Pelicano", true)
            .click()
    }

    private fun seeLocationList() {
        val locationListView = UiScrollable(UiSelector().scrollable(true))
        assertThat(locationListView.childCount).isGreaterThan(0)
    }

    private fun getLauncherPackageName(): String {
        // Create launcher Intent
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        val packageManager = getApplicationContext<Context>().packageManager
        val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo?.activityInfo?.packageName
            ?: throw Throwable("Unable to start main activity for package $PACKAGE")
    }

    companion object {
        private const val LAUNCH_TIMEOUT = 5000L
        private const val PACKAGE = "com.favoriteplaces"
    }
}