package com.favoriteplaces.location.list.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.favoriteplaces.core.binding.BindingFragment
import com.favoriteplaces.core.control.HomeAction
import com.favoriteplaces.core.control.sendHomeAction
import com.favoriteplaces.location.R
import com.favoriteplaces.location.databinding.LocationListFragmentBinding
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationListFragment :
    BindingFragment<LocationListFragmentBinding>(R.layout.location_list_fragment) {

    @Inject
    internal lateinit var viewModel: LocationListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchLocations()
    }

    override fun onStart() {
        super.onStart()
        requireContext().sendHomeAction(HomeAction(isNavBarVisible = true))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewBinding(LocationListFragmentBinding.bind(view))

        setupLocationsRecycler()
        setupObservers()
        setupListeners()
    }

    private fun setupLocationsRecycler() {
        binding.locationRecyclerView.adapter = LocationAdapter()
        (binding.locationRecyclerView.layoutManager as StaggeredGridLayoutManager).spanCount = 2
    }

    private fun setupObservers() {
        viewModel.instruction.observe(viewLifecycleOwner) { onInstructionChange(it) }
        viewModel.locationList.observe(viewLifecycleOwner) { onLocationListResult(it) }
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