package com.favoriteplaces.location.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.SingleLiveEvent
import com.favoriteplaces.location.LocationInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val instruction: LocationDetailInstruction,
    private val interactor: LocationInteractor
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

//    private val _locationList = MutableLiveData<List<LocationUIModel>>()
//    val locationList: LiveData<List<LocationUIModel>> = _locationList

    fun loadLocationDetails(locationId: Int) {
        viewModelScope.launch {
            interactor.loadLocationDetails(locationId)
        }
    }
}