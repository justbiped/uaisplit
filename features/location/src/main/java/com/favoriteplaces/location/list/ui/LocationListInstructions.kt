package com.favoriteplaces.location.list.ui

import androidx.navigation.NavDirections
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject


internal interface Instruction {
    data class Success(val locations: List<LocationUIModel>) : Instruction
    object Failure : Instruction
    object Loading : Instruction
    data class Navigation(val destination: NavDirections) : Instruction
}

internal class LocationListInstructions @Inject constructor() {
    fun success(locations: List<LocationUIModel>) = Instruction.Success(locations)
    fun failure() = Instruction.Failure
    fun loading() = Instruction.Loading

    fun navigateToLocationDetails(locationUIModel: LocationUIModel) = Instruction
        .Navigation(LocationListFragmentDirections.toLocationDetails(locationUIModel.id))
}
