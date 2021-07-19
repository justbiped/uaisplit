package com.hotmart.test

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import com.favoriteplaces.home.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

class LocationListSteps {

    private val device = UiDevice.getInstance(getInstrumentation())
    private val context = getApplicationContext<Context>()

    @Before
    fun startMainActivityFromHomeScreen() {
        device.pressHome()
    }

    @Given("that i have the Location app")
    fun isLocationAppInstalled() {
        val launcherPackage: String = getLauncherPackageName()
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)
    }

    @When("i open it")
    fun launchLocationApp() {
        val intent: Intent? = context.packageManager.getLaunchIntentForPackage(PACKAGE)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.clazz(MainActivity::class.java)), LAUNCH_TIMEOUT)
    }

    @Then("i see the list of location at home screen")
    fun checkLocationListIsDisplayed() {
        val locationListView = UiScrollable(UiSelector().scrollable(true))
        assertThat(locationListView.childCount).isGreaterThan(0)
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

    private fun getLauncherPackageName(): String {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)

        val resolveInfo = getApplicationContext<Context>()
            .packageManager
            .resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolveInfo?.activityInfo?.packageName ?: throw Throwable("Unable to start $PACKAGE")
    }

    companion object {
        private const val LAUNCH_TIMEOUT = 5000L
        private const val PACKAGE = "com.favoriteplaces"
    }
}