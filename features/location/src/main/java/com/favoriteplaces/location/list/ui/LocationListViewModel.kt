package com.favoriteplaces.location.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favoriteplaces.core.flow.Instruction
import com.favoriteplaces.core.flow.MutableInstructionFlow
import com.favoriteplaces.core.tools.DispatcherProvider
import com.favoriteplaces.location.list.LoadLocations
import com.favoriteplaces.location.list.data.Location
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class LocationListViewModel
@Inject constructor(
    private val locationListInstructions: LocationListInstructions,
    private val loadLocations: LoadLocations
) : ViewModel() {

    private val _instruction = MutableInstructionFlow<Instruction>()
    val instruction = _instruction.toInstructionFlow()

    fun fetchLocations() {

        viewModelScope.launch(DispatcherProvider.IO) {
            _instruction.emit(locationListInstructions.loading())

            val locationResult = loadLocations()
            locationResult.onSuccess { locations ->
                onLocationLoadSuccess(locations)
            }

            locationResult.onFailure {
                _instruction.emit(locationListInstructions.failure())
            }
        }
    }

    private suspend fun onLocationLoadSuccess(locations: List<Location>) {
        val locationsUI = locations.map { LocationUIModel.fromDomain(it) }

        _instruction.emit(locationListInstructions.success(locationsUI))
    }

    fun onLocationSelected(locationUIModel: LocationUIModel) {
        viewModelScope.launch {
            _instruction.emit(locationListInstructions.navigateToLocationDetails(locationUIModel))
        }
    }
}