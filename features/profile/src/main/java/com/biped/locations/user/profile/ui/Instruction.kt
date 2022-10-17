package com.biped.locations.user.profile.ui

internal sealed interface Instruction {
    object Default : Instruction
    object Loading : Instruction
}
