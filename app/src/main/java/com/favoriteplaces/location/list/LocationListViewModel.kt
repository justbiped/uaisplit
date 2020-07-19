package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.location.data.LocationHttpClient
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListViewModel @Inject constructor(locationHttpClient: LocationHttpClient) :
    ViewModel() {

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    init {

        viewModelScope.launch {
            val locationsListRemote = locationHttpClient.fetchLocations()
            val locations = locationsListRemote.toDomainLocations()
            val locationsUI = locations.map { LocationUIModel.fromDomain(it) }

            _locationList.postValue(locationsUI)
        }
    }
}