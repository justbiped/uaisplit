package com.favoriteplaces.core.extensions

import androidx.navigation.NavController
import com.favoriteplaces.core.tools.Navigation

fun NavController.navigate(navigation: Navigation) {
    navigate(navigation.destination, navigation.bundledArgs)
}