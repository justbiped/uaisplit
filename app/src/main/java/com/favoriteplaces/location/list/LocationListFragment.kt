package com.favoriteplaces.location.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.favoriteplaces.R

class LocationListFragment : Fragment(R.layout.location_list_fragment) {

    private val viewModel = LocationListViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLocationsRecycler()
        setupObservers()
    }

    private fun setupLocationsRecycler() {
    }

    private fun setupObservers() {
        viewModel.locationList.observe(viewLifecycleOwner, Observer {onLocationListResult()})
    }

    private fun onLocationListResult() {

    }
}