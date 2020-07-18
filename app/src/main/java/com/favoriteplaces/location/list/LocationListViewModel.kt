package com.favoriteplaces.location.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationListViewModel : ViewModel() {

    private val _locationList = MutableLiveData<List<LocationUIModel>>()
    val locationList: LiveData<List<LocationUIModel>> = _locationList

    init {
        val locations = listOf(
            LocationUIModel(0, "Meu Restaurante", 3, "Restaurante", "https://images.pexels.com/photos/2696064/pexels-photo-2696064.jpeg?cs=srgb&dl=man-and-woman-wearing-black-and-white-striped-aprons-2696064.jpg&fm=jpg"),
            LocationUIModel(1, "Outro", 4, "Restaurante", "https://images.pexels.com/photos/2696064/pexels-photo-2696064.jpeg?cs=srgb&dl=man-and-woman-wearing-black-and-white-striped-aprons-2696064.jpg&fm=jpg"),
            LocationUIModel(2, "Mais Um", 5, "Restaurante", "https://images.pexels.com/photos/2696064/pexels-photo-2696064.jpeg?cs=srgb&dl=man-and-woman-wearing-black-and-white-striped-aprons-2696064.jpg&fm=jpg"),
            LocationUIModel(3, "Pior", 1, "Restaurante", "https://images.pexels.com/photos/2696064/pexels-photo-2696064.jpeg?cs=srgb&dl=man-and-woman-wearing-black-and-white-striped-aprons-2696064.jpg&fm=jpg")
        )

        _locationList.postValue(locations)
    }
}