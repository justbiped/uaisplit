package com.hotmart.locations.core.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.changeVisibility(isVisible: Boolean) {
    if (isVisible) {
        animate().translationY(0f)
            .withStartAction { this.isVisible = isVisible }
            .start()
    } else {
        animate().translationY(y)
            .withEndAction { this.isVisible = isVisible }
            .start()
    }
}