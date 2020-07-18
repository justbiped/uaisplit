package com.favoriteplaces.location.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.android.synthetic.main.location_list_fragment.*

class LocationListFragment : Fragment(R.layout.location_list_fragment) {

    private val viewModel = LocationListViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLocationsRecycler()
        setupObservers()
    }

    private fun setupLocationsRecycler() {
        locationRecyclerView.adapter = LocationAdapter()
        (locationRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
    }

    private fun setupObservers() {
        viewModel.locationList.observe(viewLifecycleOwner, Observer { onLocationListResult(it) })
    }

    private fun onLocationListResult(locationList: List<LocationUIModel>) {
        getLocationAdapter()?.also {
            it.submitList(locationList)
        }
    }

    private fun getLocationAdapter() = (locationRecyclerView.adapter as? LocationAdapter)
}