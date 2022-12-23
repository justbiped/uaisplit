package biped.works.test.instrumentation.action

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import java.util.concurrent.TimeoutException
import org.hamcrest.Matcher

object TimeoutViewInteraction {
    /**
     * @param timeout is in Milliseconds
     */
    private fun wait(viewMatcher: Matcher<View>, timeout: Long): ViewAction {
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
                    uiController.loopMainThreadForAtLeast(100)
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
    fun waitView(viewMatcher: Matcher<View>, timeout: Long = 2000): ViewInteraction {
        return onView(isRoot()).perform(wait(viewMatcher, timeout))
    }
}
