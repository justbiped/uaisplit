package com.favoriteplaces.core.extensions

import androidx.activity.addCallback
import androidx.fragment.app.Fragment

fun Fragment.onBackPressCallback(callback: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback { callback() }
}