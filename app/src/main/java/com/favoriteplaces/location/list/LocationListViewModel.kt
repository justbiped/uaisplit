package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListViewModel @Inject constructor(private val locationInteractor: LocationInteractor) :
    ViewModel() {

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    init {

        viewModelScope.launch {
            val locationResult = locationInteractor.loadLocations()

            locationResult.onSuccess { locations ->
                val locationsUI = locations.map { LocationUIModel.fromDomain(it) }
                _locationList.postValue(locationsUI)
            }
        }
    }
}