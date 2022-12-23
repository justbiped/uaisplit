package biped.works.test.instrumentation.action

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

fun isVisible(): ViewAssertion {
    return ViewAssertions.matches(ViewMatchers.isDisplayed())
}

fun hasText(text: String): ViewAssertion {
    return ViewAssertions.matches(ViewMatchers.withText(text))
}