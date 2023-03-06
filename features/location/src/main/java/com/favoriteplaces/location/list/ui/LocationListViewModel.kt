package com.favoriteplaces.location.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biped.works.coroutines.MutableInstructionFlow
import biped.works.coroutines.launchIO
import com.favoriteplaces.location.list.LoadLocationsUseCase
import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LocationListViewModel
@Inject constructor(
    private val locationListInstructions: LocationListInstructions,
    private val loadLocations: LoadLocationsUseCase
) : ViewModel() {

    private val _instruction = MutableInstructionFlow(locationListInstructions.default())
    val instruction = _instruction.toInstructionFlow()

    fun fetchLocations() {
        _instruction.post(locationListInstructions.loading())

        viewModelScope.launchIO {
            loadLocations()
                .onSuccess { onLocationLoadSuccess(it) }
                .onFailure {
                    _instruction.emit(locationListInstructions.failure())
                    _instruction.post(locationListInstructions.default())
                }
        }
    }

    private fun onLocationLoadSuccess(locations: List<Location>) {
        val locationsUI = locations.map { LocationUIModel.fromDomain(it) }
        _instruction.post(locationListInstructions.success(locationsUI))
    }

    fun onLocationSelected(locationUIModel: LocationUIModel) {
        _instruction.emit(locationListInstructions.navigateToLocationDetails(locationUIModel))
    }
}