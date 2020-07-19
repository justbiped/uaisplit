package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.eatat.core.tools.Instruction
import com.favoriteplaces.location.Location
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListViewModel
@Inject constructor(
    private val instruction: LocationListViewInstruction,
    private val locationInteractor: LocationInteractor
) : ViewModel() {

    private val _viewInstruction = MutableLiveData<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    init {
        loadLocations()
    }

    private fun loadLocations() {
        _viewInstruction.postValue(instruction.loading())

        viewModelScope.launch {
            val locationResult = locationInteractor.loadLocations()

            locationResult.onSuccess { locations ->
                onLocationLoadSuccess(locations)
            }

            locationResult.onFailure {
                _viewInstruction.postValue(instruction.failure())
            }
        }
    }

    private fun onLocationLoadSuccess(locations: List<Location>) {
        val locationsUI = locations.map { LocationUIModel.fromDomain(it) }

        _locationList.postValue(locationsUI)
        _viewInstruction.postValue(instruction.success())
    }

    fun onLocationSelected(locationUIModel: LocationUIModel) {
        _viewInstruction.postValue(instruction.navigateToLocationDetails(locationUIModel))
    }
}