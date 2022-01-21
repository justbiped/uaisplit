package com.favoriteplaces.location.di

import com.favoriteplaces.location.detail.DetailComponent
import com.favoriteplaces.location.detail.DetailModule
import com.favoriteplaces.location.list.di.ListComponent
import com.favoriteplaces.location.list.di.ListModule
import com.hotmart.locations.core.injection.CoreComponent
import com.hotmart.locations.core.injection.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [LocationModule::class, ListModule::class, DetailModule::class],
    dependencies = [CoreComponent::class]
)
interface LocationComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): LocationComponent
    }

    fun listComponent(): ListComponent.Factory

    fun detailComponent(): DetailComponent.Factory
}