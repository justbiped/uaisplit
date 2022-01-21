package com.favoriteplaces.location.list.di

import com.favoriteplaces.location.list.ui.LocationListFragment
import dagger.Module
import dagger.Subcomponent

@Subcomponent
interface ListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }

    fun inject(fragment: LocationListFragment)
}

@Module(subcomponents = [ListComponent::class])
class ListModule