package com.favoriteplaces.core.extensions

import android.content.Context
import android.content.ContextWrapper
import com.favoriteplaces.core.injection.CoreComponent
import com.favoriteplaces.core.injection.CoreInjector

fun Context.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}

fun ContextWrapper.getCoreComponent(): CoreComponent {
    return (applicationContext as CoreInjector).coreComponent
}