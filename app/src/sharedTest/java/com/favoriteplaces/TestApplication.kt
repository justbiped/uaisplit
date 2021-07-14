package com.favoriteplaces

import android.app.Application
import com.favoriteplaces.core.injection.CoreComponent
import com.favoriteplaces.core.injection.CoreInjector
import com.favoriteplaces.core.injection.DaggerCoreComponent

class TestApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }
}