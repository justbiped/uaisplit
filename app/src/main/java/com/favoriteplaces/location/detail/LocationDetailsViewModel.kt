package com.favoriteplaces.location.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.SingleLiveEvent
import com.favoriteplaces.location.LocationInteractor
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    private val instruction: LocationDetailsInstruction,
    private val interactor: LocationInteractor
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

//    private val _locationList = MutableLiveData<List<LocationUIModel>>()
//    val locationList: LiveData<List<LocationUIModel>> = _locationList

    fun loadLocationDetails(locationId:Int) {
        interactor.loadLocationDetails(locationId)
    }
}