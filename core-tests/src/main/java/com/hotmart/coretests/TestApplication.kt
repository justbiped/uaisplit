package com.hotmart.coretests

import android.app.Application
import com.hotmart.locations.core.injection.CoreComponent
import com.hotmart.locations.core.injection.CoreInjector
import com.hotmart.locations.core.injection.DaggerCoreComponent

class TestApplication : Application(), CoreInjector {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }
}