package com.favoriteplaces.location.list.ui

import androidx.navigation.NavDirections
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject

internal sealed interface Instruction {
    data class Success(val locations: List<LocationUIModel>) : Instruction
    object Failure : Instruction
    object Default : Instruction
    object Loading : Instruction
    data class Navigation(val destination: NavDirections) : Instruction
}

internal class LocationListInstructions @Inject constructor() {
    fun default(): Instruction = Instruction.Default
    fun loading(): Instruction = Instruction.Loading
    fun failure(): Instruction = Instruction.Failure

    fun success(locations: List<LocationUIModel>): Instruction = Instruction.Success(locations)

    fun navigateToLocationDetails(locationUIModel: LocationUIModel) = Instruction
        .Navigation(LocationListFragmentDirections.toLocationDetails(locationUIModel.id))
}
