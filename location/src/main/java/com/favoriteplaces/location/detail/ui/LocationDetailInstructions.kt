package com.favoriteplaces.location.detail.ui

import javax.inject.Inject

internal sealed interface Instruction {
    object Failure : Instruction
}

internal class LocationDetailInstructions @Inject constructor() {
    fun failure() = Instruction.Failure
}
