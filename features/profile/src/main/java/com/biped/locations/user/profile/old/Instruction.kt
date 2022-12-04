package com.biped.locations.user.profile.old

import com.biped.locations.user.profile.data.User

internal sealed interface Instruction {
    data class UpdateUser(val user: User) : Instruction
    object Default : Instruction
    object Loading : Instruction
}
