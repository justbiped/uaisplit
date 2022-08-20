package com.favoriteplaces.location.list.ui

import androidx.navigation.NavDirections
import com.favoriteplaces.core.flow.BufferedInstruction
import com.favoriteplaces.core.flow.Instruction
import com.favoriteplaces.location.list.data.ui.LocationUIModel
import javax.inject.Inject


internal data class Success(val locations: List<LocationUIModel>) : BufferedInstruction
internal object Failure : BufferedInstruction
internal object Loading : BufferedInstruction
internal data class Navigation(val destination: NavDirections) : Instruction

internal class LocationListInstructions @Inject constructor() {
    fun success(locations: List<LocationUIModel>) = Success(locations)
    fun failure() = Failure
    fun loading() = Loading

    fun navigateToLocationDetails(locationUIModel: LocationUIModel) = Navigation(
        LocationListFragmentDirections.toLocationDetails(locationUIModel.id)
    )
}
