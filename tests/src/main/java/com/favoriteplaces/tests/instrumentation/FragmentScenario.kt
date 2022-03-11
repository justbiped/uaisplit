package com.favoriteplaces.tests.instrumentation

import android.content.ComponentName
import android.content.Intent.makeMainActivity
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.favoriteplaces.tests.R
import dagger.hilt.android.testing.HiltTestApplication

const val THEME_EXTRAS_BUNDLE_KEY = "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY"
const val FRAGMENT_TAG = "FRAGMENT_UNDER_TEST_TAG"

inline fun <reified T : Fragment> fragmentScenario(
    fragmentArgs: Bundle = Bundle(),
    @StyleRes themeResId: Int = R.style.AppTheme,
): FragmentScenario<T> {
    val context = ApplicationProvider.getApplicationContext<HiltTestApplication>()
    val intent = makeMainActivity(ComponentName(context, HiltTestActivity::class.java))
    intent.putExtra(THEME_EXTRAS_BUNDLE_KEY, themeResId)
    return FragmentScenario.launch(launch(intent), T::class.java, fragmentArgs)
}

class FragmentScenario<T : Fragment> private constructor(
    private val scenario: ActivityScenario<HiltTestActivity>,
    private val fragmentClass: Class<T>,
    private val fragmentArgs: Bundle
) {

    private var onFragment: (T) -> Unit = {}
    private lateinit var fragment: T

    init {
        scenario.onActivity { setupFragmentScenario(it) }
    }

    fun moveToState(state: Lifecycle.State) = scenario.moveToState(state)

    fun recreate() {
        scenario.onActivity { setupFragmentScenario(it) }
    }

    fun withFragment(onFragment: T.() -> Unit) {
        UiThreadStatement.runOnUiThread {
            this.onFragment = onFragment
            if (this::fragment.isInitialized) {
                onFragment(fragment)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupFragmentScenario(activity: HiltTestActivity) {
        val factory = activity.supportFragmentManager.fragmentFactory
        val fragment = factory.instantiate(
            checkNotNull(fragmentClass.classLoader), fragmentClass.name
        )
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, fragment, FRAGMENT_TAG)
            .commitNow()

        check(fragmentClass.isInstance(fragment))
        setFragment(fragment as T)
    }

    private fun setFragment(fragment: T) {
        this.fragment = fragment
        onFragment(fragment)
    }

    companion object {
        fun <T : Fragment> launch(
            scenario: ActivityScenario<HiltTestActivity>,
            fragmentClass: Class<T>,
            fragmentArgs: Bundle
        ): FragmentScenario<T> {
            return FragmentScenario(scenario, fragmentClass, fragmentArgs)
        }
    }
}
