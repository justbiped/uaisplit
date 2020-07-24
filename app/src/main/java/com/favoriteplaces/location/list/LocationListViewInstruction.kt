package com.favoriteplaces.location.list

import com.downstairs.eatat.core.tools.Navigation
import com.downstairs.eatat.core.tools.ViewInstruction
import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject

class LocationListViewInstruction @Inject constructor() : ViewInstruction() {

    companion object {
        const val LOCATION_ID_KEY = "locationIdKey"
    }

    fun navigateToLocationDetails(locationUIModel: LocationUIModel): Navigation {
        return Navigation(R.id.fromLocationListToLocationDetails).apply {
            putArguments(LOCATION_ID_KEY to locationUIModel.id)
        }
    }
}
