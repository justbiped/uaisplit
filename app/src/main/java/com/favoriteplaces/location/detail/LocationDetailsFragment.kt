package com.favoriteplaces.location.details

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.api.load
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.hideHomeNavigationBar
import kotlinx.android.synthetic.main.location_details_fragment.*

class LocationDetailsFragment : Fragment(R.layout.location_details_fragment) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideHomeNavigationBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupStatusBar()
        locationDetailsImageView.load("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?cs=srgb&dl=adult-beard-boy-casual-220453.jpg&fm=jpg")
    }

    private fun setupStatusBar() {
        requireActivity().window.apply {
            statusBarColor = Color.TRANSPARENT
        }
    }
}