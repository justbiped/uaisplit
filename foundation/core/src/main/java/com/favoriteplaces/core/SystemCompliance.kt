package com.favoriteplaces.core

import android.os.Build

object SystemCompliance {
    private val sdk = Build.VERSION.SDK_INT
    fun isDynamicColorSupported() = sdk >= Build.VERSION_CODES.S
}