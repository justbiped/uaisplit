package com.favoriteplaces.location

import androidx.fragment.app.Fragment
import com.favoriteplaces.location.di.DaggerLocationComponent
import com.favoriteplaces.location.di.LocationComponent
import com.hotmart.locations.core.extensions.getCoreComponent
import com.hotmart.locations.core.injection.FeatureInjector

class LocationHostFragment :
    Fragment(R.layout.location_host_fragment),
    FeatureInjector<LocationComponent> {

    override val component: LocationComponent by lazy {
        DaggerLocationComponent.factory().create(requireActivity().getCoreComponent())
    }
}