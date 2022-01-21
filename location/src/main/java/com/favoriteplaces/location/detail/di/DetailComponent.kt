package com.favoriteplaces.location.detail

import com.favoriteplaces.location.detail.ui.LocationDetailFragment
import dagger.Module
import dagger.Subcomponent

@Subcomponent
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(fragment: LocationDetailFragment)
}

@Module(subcomponents = [DetailComponent::class])
class DetailModule