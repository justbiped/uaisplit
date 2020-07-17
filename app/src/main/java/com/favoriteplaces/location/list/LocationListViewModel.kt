package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationListViewModel : ViewModel() {

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    init {
        val locations = listOf(
            LocationUIModel(0, "Meu Restaurante", 3, "Restaurante"),
            LocationUIModel(1, "Outro", 4, "Restaurante"),
            LocationUIModel(2, "Mais Um", 5, "Restaurante"),
            LocationUIModel(3, "Pior", 1, "Restaurante")
        )

        _locationList.postValue(locations)
    }
}