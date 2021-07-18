package com.favoriteplaces.location.detail.ui

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
import com.favoriteplaces.core.extensions.getComponent
import com.favoriteplaces.core.extensions.hideHomeNavigationBar
import com.favoriteplaces.core.extensions.onBackPressCallback
import com.favoriteplaces.databinding.LocationDetailFragmentBinding
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.favoriteplaces.location.injection.LocationComponent
import com.favoriteplaces.location.list.ui.LocationListViewInstruction.Companion.LOCATION_ID_KEY
import javax.inject.Inject

class LocationDetailFragment : Fragment(R.layout.location_detail_fragment) {
    @Inject
    lateinit var viewModel: LocationDetailViewModel

    private lateinit var biding: LocationDetailFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideHomeNavigationBar()

        getComponent<LocationComponent>().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressCallback {
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        biding = LocationDetailFragmentBinding.bind(view)

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
        biding.locationDetailToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupReviewRecycler() {
        biding.locationReviewList.isNestedScrollingEnabled = false
        biding.locationReviewList.adapter = LocationReviewAdapter()
    }

    private fun setupObservers() {
        viewModel.viewInstruction.observe(viewLifecycleOwner, Observer { onInstructionChange(it) })
        viewModel.locationDetail.observe(viewLifecycleOwner, Observer { locationDetail ->
            bindLocationDetail(locationDetail)
        })
    }

    private fun onInstructionChange(instruction: Instruction) {
        when (instruction) {
            is Failure -> onDetailLoadFailure()
        }
    }

    private fun bindLocationDetail(locationDetail: LocationDetailUIModel) {
        biding.locationDetailNameText.text = locationDetail.name
        biding.locationDetailsRatingBar.rating = locationDetail.rating.toFloat()
        biding.locationDetailRatingText.text = "${locationDetail.rating}"
        biding.locationDetailAboutText.text = locationDetail.about

        biding.locationDetailSchedule.locationDetailScheduleText.text =
            locationDetail.formattedSchedule(ScheduleFormatter(requireContext()))

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