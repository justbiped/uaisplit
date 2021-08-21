package com.hotmart.locations.core.extensions

import androidx.navigation.NavController
import com.hotmart.locations.core.tools.Navigation

fun NavController.navigate(navigation: Navigation) {
    navigate(navigation.destination, navigation.bundledArgs)
}