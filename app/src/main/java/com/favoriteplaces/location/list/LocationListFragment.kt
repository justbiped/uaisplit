package com.favoriteplaces.location.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.favoriteplaces.R
import com.favoriteplaces.core.injection.CoreInjector
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.android.synthetic.main.location_list_fragment.*
import javax.inject.Inject

class LocationListFragment : Fragment(R.layout.location_list_fragment) {

    @Inject
    lateinit var viewModel: LocationListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerLocationComponent.factory()
            .create((context.applicationContext as CoreInjector).coreComponent)
            .inject(this)
    }

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