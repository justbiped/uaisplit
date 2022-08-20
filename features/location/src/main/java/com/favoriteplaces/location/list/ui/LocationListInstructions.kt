package com.favoriteplaces.location.list.ui

import androidx.navigation.NavDirections
import com.favoriteplaces.core.flow.BufferedInstruction
import com.favoriteplaces.core.flow.Instruction
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject


internal interface LocationListInstruction {
    data class Success(val locations: List<LocationUIModel>) : BufferedInstruction
    object Failure : BufferedInstruction
    object Loading : BufferedInstruction
    data class Navigation(val destination: NavDirections) : Instruction
}

internal class LocationListInstructions @Inject constructor() {
    fun success(locations: List<LocationUIModel>) = LocationListInstruction.Success(locations)
    fun failure() = LocationListInstruction.Failure
    fun loading() = LocationListInstruction.Loading

    fun navigateToLocationDetails(locationUIModel: LocationUIModel) = LocationListInstruction
        .Navigation(LocationListFragmentDirections.toLocationDetails(locationUIModel.id))
}
