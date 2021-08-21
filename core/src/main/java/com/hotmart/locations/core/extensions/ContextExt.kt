package com.hotmart.locations.core.extensions

import android.content.Context
import android.content.ContextWrapper
import com.hotmart.locations.core.injection.CoreComponent
import com.hotmart.locations.core.injection.CoreInjector

fun Context.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}

fun ContextWrapper.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}