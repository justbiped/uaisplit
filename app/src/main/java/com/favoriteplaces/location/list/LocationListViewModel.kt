package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationListViewModel : ViewModel() {

    private val _locationList = MutableLiveData<LocationUIModel>()
    val locationList: LiveData<LocationUIModel> = _locationList
}