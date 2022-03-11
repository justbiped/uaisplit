package com.favoriteplaces.location.list.ui

import androidx.navigation.NavDirections
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject

sealed interface Instruction {
    object Success : Instruction
    object Failure : Instruction
    object Loading : Instruction
    data class Navigation(val destination: NavDirections) : Instruction
}

internal class LocationListInstructions @Inject constructor(){
    fun success() = Instruction.Success
    fun failure() = Instruction.Failure
    fun loading() = Instruction.Loading
    fun navigateToLocationDetails(locationUIModel: LocationUIModel) =
        Instruction.Navigation(LocationListFragmentDirections.toLocationDetails(locationUIModel.id))
}
