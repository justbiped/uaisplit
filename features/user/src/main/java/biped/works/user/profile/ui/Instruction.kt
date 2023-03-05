package biped.works.user.profile.ui

import biped.works.coroutines.MutableViewStateFlow
import biped.works.user.profile.data.User

internal sealed interface Instruction {
    data class UpdateUser(
        val user: User = User(),
        val isLong: Boolean = false
    ) : Instruction

    object ProfileSaved : Instruction
}

internal fun MutableViewStateFlow<Instruction>.update(
    action: Instruction.UpdateUser.() -> Instruction.UpdateUser
) {
    (value as? Instruction.UpdateUser)?.also { value = action(it) }
}