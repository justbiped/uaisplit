package com.favoriteplaces.location.detail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.getCoreComponent
import com.favoriteplaces.core.extensions.hideHomeNavigationBar
import com.favoriteplaces.location.detail.tools.ScheduleFormatter
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.list.LocationListViewInstruction.Companion.LOCATION_ID_KEY
import kotlinx.android.synthetic.main.location_detail_fragment.*
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
        setupObservers()

        val locationId = arguments?.getInt(LOCATION_ID_KEY) ?: -1
        viewModel.loadLocationDetails(locationId)
    }

    private fun setupStatusBar() {
        requireActivity().window.apply {
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setupObservers() {
        viewModel.locationDetail.observe(viewLifecycleOwner, Observer { locationDetail ->
            bindLocationDetail(locationDetail)
        })
    }

    private fun bindLocationDetail(locationDetail: LocationDetailUIModel) {
        locationDetailsNameText.text = locationDetail.name
        locationDetailsRatingBar.rating = locationDetail.review.toFloat()
        locationDetailRatingText.text = "${locationDetail.review}"
        locationDetailAboutText.text = locationDetail.about

        scheduleTextView.text =
            locationDetail.formattedSchedule(ScheduleFormatter(requireContext()))

    }
}