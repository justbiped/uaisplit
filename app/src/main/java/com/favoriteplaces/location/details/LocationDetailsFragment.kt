package com.favoriteplaces.location.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.api.load
import com.favoriteplaces.R
import kotlinx.android.synthetic.main.location_details_fragment.*

class LocationDetailsFragment : Fragment(R.layout.location_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        testImageView.load("https://images.pexels.com/photos/2696064/pexels-photo-2696064.jpeg?cs=srgb&dl=man-and-woman-wearing-black-and-white-striped-aprons-2696064.jpg&fm=jpg"){
            placeholder(R.drawable.ic_small_star_on)
        }
    }
}