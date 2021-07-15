package com.favoriteplaces.location.injection

import com.favoriteplaces.core.injection.CoreComponent
import com.favoriteplaces.core.injection.FeatureScope
import com.favoriteplaces.location.detail.ui.LocationDetailFragment
import com.favoriteplaces.location.list.ui.LocationListFragment
import dagger.Component

@FeatureScope
@Component(modules = [LocationModule::class], dependencies = [CoreComponent::class])
interface LocationComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): LocationComponent
    }

    fun inject(fragment: LocationListFragment)

    fun inject(fragment: LocationDetailFragment)
}