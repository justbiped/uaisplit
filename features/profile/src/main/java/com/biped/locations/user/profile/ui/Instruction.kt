package com.biped.locations.user.profile.ui

internal sealed interface Instruction {
    data class UpdateUser(val user: UserUiModel) : Instruction
    object Default : Instruction
    object Loading : Instruction
}