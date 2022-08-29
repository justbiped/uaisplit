package com.favoriteplaces.location.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.core.coroutines.MutableWarmFlow
import com.favoriteplaces.core.coroutines.launchIO
import com.favoriteplaces.location.detail.GetLocationDetails
import com.favoriteplaces.location.detail.data.domain.LocationDetail
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import javax.inject.Inject

internal class LocationDetailViewModel @Inject constructor(
    private val detailsInstructions: LocationDetailInstructions,
    private val getLocationDetails: GetLocationDetails,
    private val scheduleFormatter: ScheduleFormatter
) : ViewModel() {

    private val _viewInstruction = MutableWarmFlow(detailsInstructions.default())
    val viewInstruction = _viewInstruction.toWarmFlow()

    fun loadLocationDetails(locationId: Int) {
        viewModelScope.launchIO {
            val result = getLocationDetails(locationId)

            result.onSuccess { onLoadLocationDetailSuccess(it) }
            result.onFailure {
                _viewInstruction.repost()
                _viewInstruction.emit(detailsInstructions.failure())
            }
        }
    }

    private suspend fun onLoadLocationDetailSuccess(location: LocationDetail) {
        val locationDetailUiModel = LocationDetailUIModel.fromDomain(location, scheduleFormatter)
        _viewInstruction.emit(Instruction.Success(locationDetailUiModel))
    }
}
