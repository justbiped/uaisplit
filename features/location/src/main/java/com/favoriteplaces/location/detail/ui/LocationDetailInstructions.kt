package com.favoriteplaces.location.detail.ui

import com.favoriteplaces.core.flow.Instruction
import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import javax.inject.Inject

internal object Failure : Instruction
internal data class Success(val locationDetailUiModel: LocationDetailUIModel) : Instruction

internal class LocationDetailInstructions @Inject constructor() {
    fun failure() = Failure
    fun success(locationDetailUiModel: LocationDetailUIModel) = Success(locationDetailUiModel)
}
