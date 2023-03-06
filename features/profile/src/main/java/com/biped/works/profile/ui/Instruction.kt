package com.biped.works.profile.ui

import biped.works.coroutines.MutableViewStateFlow

internal sealed interface Instruction {
    data class UpdateUser(
        val profile: ProfileUiModel = ProfileUiModel(),
        val isLong: Boolean = false
    ) : Instruction

    object ProfileSaved : Instruction
}

internal fun MutableViewStateFlow<Instruction>.update(
    action: Instruction.UpdateUser.() -> Instruction.UpdateUser
) {
    (value as? Instruction.UpdateUser)?.also { value = action(it) }
}