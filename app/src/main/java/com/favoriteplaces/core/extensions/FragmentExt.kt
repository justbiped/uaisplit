package com.favoriteplaces.core.extensions

import androidx.fragment.app.Fragment
import com.favoriteplaces.core.HomeController

fun Fragment.hideHomeNavigationBar() {
    (requireActivity() as HomeController).hideNavigationBar()
}