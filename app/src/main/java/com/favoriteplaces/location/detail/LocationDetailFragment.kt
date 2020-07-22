package com.favoriteplaces.location.detail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.downstairs.eatat.core.tools.Failure
import com.downstairs.eatat.core.tools.Instruction
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.getCoreComponent
import com.favoriteplaces.core.extensions.hideHomeNavigationBar
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.list.LocationListViewInstruction.Companion.LOCATION_ID_KEY
import kotlinx.android.synthetic.main.location_detail_fragment.*
import kotlinx.android.synthetic.main.location_detail_schedule.*
import javax.inject.Inject

class LocationDetailFragment : Fragment(R.layout.location_detail_fragment) {

    @Inject
    lateinit var viewModel: LocationDetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideHomeNavigationBar()

        DaggerLocationComponent.factory().create(context.getCoreComponent()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStatusBar()
        setupToolBar()
        setupReviewRecycler()
        setupObservers()

        val locationId = arguments?.getInt(LOCATION_ID_KEY) ?: -1
        viewModel.loadLocationDetails(locationId)
    }

    private fun setupStatusBar() {
        requireActivity().window.apply {
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupToolBar() {
        locationDetailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupReviewRecycler() {
        locationReviewRecycler.isNestedScrollingEnabled = false
        locationReviewRecycler.adapter = LocationReviewAdapter()
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer { onInstructionChange(it) })
        viewModel.locationDetail.observe(viewLifecycleOwner, Observer { locationDetail ->
            bindLocationDetail(locationDetail)
        })
    }

    private fun onInstructionChange(instruction: Instruction) {
        when(instruction){
            is Failure -> onDetailLoadFailure()
        }
    }

    private fun bindLocationDetail(locationDetail: LocationDetailUIModel) {
        locationDetailNameText.text = locationDetail.name
        locationDetailsRatingBar.rating = locationDetail.rating.toFloat()
        locationDetailRatingText.text = "${locationDetail.rating}"
        locationDetailAboutText.text = locationDetail.about

        locationDetailScheduleText.text =
            locationDetail.formattedSchedule(ScheduleFormatter(requireContext()))

        locationDetailPhoneText.text = locationDetail.phone
        locationDetailAddressText.text = locationDetail.address

        getReviewAdapter()?.submitList(locationDetail.locationReviews)
    }

    private fun onDetailLoadFailure() {
Toast.makeText(context, R.string.location_detail_load_error_msg, Toast.LENGTH_LONG)
    }

    private fun getReviewAdapter(): LocationReviewAdapter? {
        return locationReviewRecycler.adapter as? LocationReviewAdapter
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