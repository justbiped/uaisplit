package com.hotmart.locations.core.extensions

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.hotmart.locations.core.HomeController
import com.hotmart.locations.core.injection.FeatureInjector

fun Fragment.hideHomeNavigationBar() {
    (requireActivity() as HomeController).hideNavigationBar()
}

fun Fragment.onBackPressCallback(callback: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback { callback() }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Fragment.getComponent(): T {
    val injector = parentFragment?.parentFragment as? FeatureInjector<T>
    return injector?.component
        ?: throw Throwable("Cant fint ${T::class.simpleName} for the actual graph")
}