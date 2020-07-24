package com.favoriteplaces.location

import androidx.fragment.app.Fragment
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.getCoreComponent
import com.favoriteplaces.core.injection.FeatureInjector
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.injection.LocationComponent

class LocationHostFragment :
    Fragment(R.layout.location_host_fragment),
    FeatureInjector<LocationComponent> {

    override val component: LocationComponent by lazy {
        DaggerLocationComponent.factory().create(requireActivity().getCoreComponent())
    }
}