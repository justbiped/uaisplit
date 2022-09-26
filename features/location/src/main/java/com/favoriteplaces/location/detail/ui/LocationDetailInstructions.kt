package com.favoriteplaces.location.detail.ui

import com.favoriteplaces.location.detail.data.ui.LocationDetailUIModel
import javax.inject.Inject

internal sealed interface Instruction {
    object Default : Instruction
    object Failure : Instruction
    data class Success(val locationDetailUiModel: LocationDetailUIModel) : Instruction
}

internal class LocationDetailInstructions @Inject constructor() {
    fun default(): Instruction = Instruction.Default
    fun failure(): Instruction = Instruction.Failure
    fun success(locationDetailUiModel: LocationDetailUIModel) =
        Instruction.Success(locationDetailUiModel)
}
