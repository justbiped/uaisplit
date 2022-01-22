package com.hotmart.locations.core.extensions

import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.hotmart.locations.core.HomeController

fun Fragment.hideHomeNavigationBar() {
    (requireActivity() as HomeController).hideNavigationBar()
}

fun Fragment.onBackPressCallback(callback: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback { callback() }
}