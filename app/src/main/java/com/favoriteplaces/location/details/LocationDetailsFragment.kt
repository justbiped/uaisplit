package com.favoriteplaces.location.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.favoriteplaces.R
import com.favoriteplaces.core.extensions.hideHomeNavigationBar

class LocationDetailsFragment : Fragment(R.layout.location_details_fragment) {

    override fun onAttach(context: Context) {
        super.onAttach(context)

        hideHomeNavigationBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}