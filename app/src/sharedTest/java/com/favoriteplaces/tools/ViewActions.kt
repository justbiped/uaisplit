package com.favoriteplaces.tools

import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException

/**
 * @param timeout is in Milliseconds
 */
fun wait(viewMatcher: Matcher<View>, timeout: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return viewMatcher.toString()
        }

        override fun perform(uiController: UiController, view: View?) {
            uiController.loopMainThreadUntilIdle()
            val endTime = System.currentTimeMillis() + timeout

            do {
                TreeIterables.breadthFirstViewTraversal(view).forEach { child ->
                    if (viewMatcher.matches(child)) return
                }
                uiController.loopMainThreadForAtLeast(50)
            } while (System.currentTimeMillis() < endTime)

            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}

/**
 * @param timeout is in Milliseconds
 */
fun findView(viewMatcher: Matcher<View>, timeout: Long = 2000): ViewInteraction {
    return onView(isRoot()).perform(wait(viewMatcher, timeout))
}