package com.favoriteplaces.injection

import com.favoriteplaces.location.list.LocationListFragmentTest
import dagger.Component

@TestScope
@Component(dependencies = [CoreComponent::class])
interface TestComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): TestComponent
    }

    fun inject(test: LocationListFragmentTest)
}