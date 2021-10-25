package com.favoriteplaces.location.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.location.detail.GetLocationDetails
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import com.hotmart.locations.core.tools.Instruction
import com.hotmart.locations.core.tools.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailViewModel @Inject constructor(
    private val instruction: LocationDetailInstruction,
    private val getLocationDetails: GetLocationDetails,
    private val scheduleFormatter: ScheduleFormatter
) : ViewModel() {

    private val _viewInstruction = SingleLiveEvent<Instruction>()
    val viewInstruction: LiveData<Instruction> = _viewInstruction

    private val _locationDetail = MutableLiveData<LocationDetailUIModel>()
    val locationDetail: LiveData<LocationDetailUIModel> = _locationDetail

    fun loadLocationDetails(locationId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getLocationDetails(locationId)

            result.onSuccess { onLoadLocationDetailSuccess(it) }
            result.onFailure { _viewInstruction.postValue(instruction.failure()) }
        }
    }

    private fun onLoadLocationDetailSuccess(location: LocationDetail) {
        _locationDetail.postValue(LocationDetailUIModel.fromDomain(location, scheduleFormatter))
    }
}