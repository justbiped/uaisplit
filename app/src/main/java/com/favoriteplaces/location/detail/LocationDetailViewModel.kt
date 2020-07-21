package com.favoriteplaces.location.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.downstairs.eatat.core.tools.Instruction
import com.downstairs.eatat.core.tools.SingleLiveEvent
import com.favoriteplaces.location.LocationInteractor
import com.favoriteplaces.location.detail.data.LocationDetail
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val instruction: LocationDetailInstruction,
    private val interactor: LocationInteractor
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    private val _locationDetail = MutableLiveData<LocationDetailUIModel>()
    val locationDetail: LiveData<LocationDetailUIModel> = _locationDetail

    fun loadLocationDetails(locationId: Int) {
        viewModelScope.launch {
            val result = interactor.loadLocationDetails(locationId)

            result.onSuccess {
                val bla = it.schedules.groupByWorkingTime()
                onLoadLocationDetailSuccess(it)
            }
        }
    }

    private fun onLoadLocationDetailSuccess(location: LocationDetail) {
        _locationDetail.postValue(LocationDetailUIModel.fromDomain(location))
    }
}