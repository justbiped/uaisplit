package com.favoriteplaces.location.detail.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.favoriteplaces.location.R
import com.favoriteplaces.location.databinding.LocationDetailFragmentBinding
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.core.control.HomeAction
import com.favoriteplaces.core.control.sendHomeAction
import com.favoriteplaces.core.extensions.onBackPressCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationDetailsFragment : Fragment(R.layout.location_detail_fragment) {

    @Inject
    internal lateinit var viewModel: LocationDetailViewModel

    private lateinit var biding: LocationDetailFragmentBinding
    private val arguments by navArgs<LocationDetailsFragmentArgs>()

    override fun onStart() {
        super.onStart()
        requireContext().sendHomeAction(HomeAction(isNavBarVisible = false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        biding = LocationDetailFragmentBinding.bind(view)

        onBackPressCallback { findNavController().navigateUp() }
        biding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        setupStatusBar()
        setupReviewRecycler()
        setupObservers()

        viewModel.loadLocationDetails(arguments.locationId)
    }

    private fun setupStatusBar() {
        requireActivity().window.apply { statusBarColor = Color.TRANSPARENT }
    }

    private fun setupReviewRecycler() {
        biding.locationReviewList.isNestedScrollingEnabled = false
        biding.locationReviewList.adapter = LocationReviewAdapter()
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner) { onInstructionChange(it) }
        viewModel.locationDetail.observe(viewLifecycleOwner) { bindLocationDetail(it) }
    }

    private fun onInstructionChange(instruction: Instruction) {
        when (instruction) {
            is Instruction.Failure -> onDetailLoadFailure()
        }
    }

    private fun bindLocationDetail(locationDetail: LocationDetailUIModel) {
        biding.collapseBar.title = locationDetail.name
        biding.locationRatingBar.rating = locationDetail.rating.toFloat()
        biding.locationRatingText.text = "${locationDetail.rating}"
        biding.locationDetailAboutText.text = locationDetail.about

        biding.locationDetailSchedule.locationDetailScheduleText.text = locationDetail.schedule

        biding.locationDetailSchedule.locationDetailPhoneText.text = locationDetail.phone
        biding.locationDetailSchedule.locationDetailAddressText.text = locationDetail.address

        getReviewAdapter()?.submitList(locationDetail.locationReviews)
    }

    private fun onDetailLoadFailure() {
        Toast.makeText(context, R.string.location_detail_load_error_msg, Toast.LENGTH_LONG).show()
    }

    private fun getReviewAdapter(): LocationReviewAdapter? {
        return biding.locationReviewList.adapter as? LocationReviewAdapter
    }

    override fun onDetach() {
        requireActivity().window.apply {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
            statusBarColor = typedValue.data
        }

        super.onDetach()
    }
}