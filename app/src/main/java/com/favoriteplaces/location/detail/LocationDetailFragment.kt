package com.favoriteplaces.location.detail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.getCoreComponent
import com.favoriteplaces.core.extensions.hideHomeNavigationBar
import com.favoriteplaces.location.injection.DaggerLocationComponent
import com.favoriteplaces.location.list.LocationListViewInstruction.Companion.LOCATION_ID_KEY
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

        val locationId = arguments?.getInt(LOCATION_ID_KEY) ?: -1
        viewModel.loadLocationDetails(locationId)
    }

    private fun setupStatusBar() {
        requireActivity().window.apply {
            statusBarColor = Color.TRANSPARENT
        }
    }
}