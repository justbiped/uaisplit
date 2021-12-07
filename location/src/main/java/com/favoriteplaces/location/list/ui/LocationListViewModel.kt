package com.favoriteplaces.location.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.location.list.LoadLocations
import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import com.hotmart.locations.core.tools.DispatcherProvider
import com.hotmart.locations.core.tools.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListViewModel
@Inject constructor(
    private val locationListInstructions: LocationListInstructions,
    private val loadLocations: LoadLocations
) : ViewModel() {

    private val _instruction = SingleLiveEvent<Instruction>()
    val instruction: LiveData<Instruction> = _instruction

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    fun fetchLocations() {
        _instruction.postValue(locationListInstructions.loading())

        viewModelScope.launch(DispatcherProvider.IO) {
            val locationResult = loadLocations()

            locationResult.onSuccess { locations ->
                onLocationLoadSuccess(locations)
            }

            locationResult.onFailure {
                _instruction.postValue(locationListInstructions.failure())
            }
        }
    }

    private fun onLocationLoadSuccess(locations: List<Location>) {
        val locationsUI = locations.map { LocationUIModel.fromDomain(it) }

        _locationList.postValue(locationsUI)
        _instruction.postValue(locationListInstructions.success())
    }

    fun onLocationSelected(locationUIModel: LocationUIModel) {
        _instruction.postValue(locationListInstructions.navigateToLocationDetails(locationUIModel))
    }
}