package com.favoriteplaces.core.extensions

import android.app.Activity
import androidx.navigation.NavController
import com.downstairs.eatat.core.tools.Navigation

fun NavController.navigateUp(activity: Activity) {
    if (!navigateUp()) {
        activity.finish()
    }
}

fun NavController.navigate(navigation: Navigation) {
    navigate(navigation.destination, navigation.bundledArgs)
}