package com.biped.locations.theme

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

@Suppress("DEPRECATION")
fun Window.statusBarColor(color: Color) {
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    if (isLightStatusBar(color)) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }else{
        decorView.systemUiVisibility = 0
    }

    statusBarColor = color.toArgb()
}

private fun isLightStatusBar(color: Color) = color.luminance() > 0.5