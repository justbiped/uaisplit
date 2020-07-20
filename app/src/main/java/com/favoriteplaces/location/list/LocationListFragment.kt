package com.favoriteplaces.location.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.Navigation
import com.downstairs.eatat.core.tools.State
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.getCoreComponent
import com.favoriteplaces.core.extensions.navigate
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
            .create(context.getCoreComponent())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLocationsRecycler()
        setupObservers()
        setupListeners()

        viewModel.loadLocations()
    }

    private fun setupLocationsRecycler() {
        locationRecyclerView.adapter = LocationAdapter()
        (locationRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer { onInstructionChange(it) })
        viewModel.locationList.observe(viewLifecycleOwner, Observer { onLocationListResult(it) })
    }

    private fun setupListeners() {
        getLocationAdapter()?.setItemClickListener {
            viewModel.onLocationSelected(it)
        }
    }

    private fun onInstructionChange(instruction: Instruction) {
        when (instruction) {
            is State.Success -> toDefaultState()
            is State.Loading -> toLoadingState()
            is State.Failed -> toDefaultState()
            is Navigation -> findNavController().navigate(instruction)
        }
    }

    private fun onLocationListResult(locationList: List<LocationUIModel>) {
        getLocationAdapter()?.also {
            it.submitList(locationList)
        }
    }

    private fun toDefaultState() {
        locationListProgressBar.isVisible = false
    }

    private fun toLoadingState() {
        locationListProgressBar.isVisible = true
    }

    private fun getLocationAdapter() = (locationRecyclerView.adapter as? LocationAdapter)
}