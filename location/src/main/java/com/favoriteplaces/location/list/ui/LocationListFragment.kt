package com.favoriteplaces.location.list.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.favoriteplaces.location.R
import com.favoriteplaces.location.databinding.LocationListFragmentBinding
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import com.hotmart.locations.core.extensions.getCoreComponent
import javax.inject.Inject

class LocationListFragment : Fragment(R.layout.location_list_fragment) {

    @Inject
    lateinit var viewModel: LocationListViewModel

    private lateinit var binding: LocationListFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerLocationComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchLocations()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = LocationListFragmentBinding.bind(view)
        setupLocationsRecycler()
        setupObservers()
        setupListeners()
    }

    private fun setupLocationsRecycler() {
        binding.locationRecyclerView.adapter = LocationAdapter()
        (binding.locationRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
    }

    private fun setupObservers() {
        viewModel.instruction.observe(viewLifecycleOwner, Observer { onInstructionChange(it) })
        viewModel.locationList.observe(viewLifecycleOwner, Observer { onLocationListResult(it) })
    }

    private fun setupListeners() {
        getLocationAdapter()?.setItemClickListener {
            viewModel.onLocationSelected(it)
        }
    }

    private fun onInstructionChange(instruction: Instruction) {
        when (instruction) {
            is Instruction.Success -> toDefaultState()
            is Instruction.Loading -> toLoadingState()
            is Instruction.Failure -> showErrorMessage()
            is Instruction.Navigation -> findNavController().navigate(instruction.destination)
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(requireContext(), "Error on load location list", Toast.LENGTH_LONG).show()
    }

    private fun onLocationListResult(locationList: List<LocationUIModel>) {
        getLocationAdapter()?.also {
            it.submitList(locationList)
        }
    }

    private fun toDefaultState() {
        binding.locationListProgressBar.isVisible = false
    }

    private fun toLoadingState() {
        binding.locationListProgressBar.isVisible = true
    }

    private fun getLocationAdapter() = (binding.locationRecyclerView.adapter as? LocationAdapter)
}