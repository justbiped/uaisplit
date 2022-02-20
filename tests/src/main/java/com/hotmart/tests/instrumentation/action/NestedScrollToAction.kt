package com.hotmart.tests.instrumentation.action

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class NestedScrollToAction : ViewAction {

    override fun getDescription() = "Scroll to a child view of a NestedScroll"

    override fun getConstraints(): Matcher<View> {
        return Matchers.allOf(
            ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
            ViewMatchers.isDescendantOfA(
                Matchers.anyOf(ViewMatchers.isAssignableFrom(NestedScrollView::class.java))
            )
        )
    }

    override fun perform(uiController: UiController, view: View) {
        if (viewIsDisplayed(view)) {
            Log.i(TAG, "View is already displayed. Returning.")
            return
        }

        scrollTo(view)
        uiController.loopMainThreadUntilIdle()
        assertScrolledTo(view)
    }

    private fun scrollTo(view: View) {
        val viewRect = Rect()
        view.getDrawingRect(viewRect)
        val hasScrolledToView = view.requestRectangleOnScreen(viewRect, true)
        if (!hasScrolledToView) {
            Log.w(TAG, "Scrolling to view was requested, but none of the parents scrolled.")
        }
    }

    private fun assertScrolledTo(view: View) {
        if (viewIsNotDisplayed(view)) {
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(
                    RuntimeException("Scrolling to view was attempted, but the view is not displayed")
                )
                .build()
        }
    }

    private fun viewIsDisplayed(view: View): Boolean {
        return isDisplayingAtLeast(90).matches(view)
    }

    private fun viewIsNotDisplayed(view: View): Boolean {
        return !isDisplayingAtLeast(90).matches(view)
    }

    companion object {
        const val TAG = "NestedScrollTo"

        fun nestedScrollTo(): ViewAction {
            return actionWithAssertions(NestedScrollToAction())
        }
    }
}
