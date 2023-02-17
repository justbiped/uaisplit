package biped.works.user.profile.ui

import biped.works.user.profile.data.User

internal sealed interface Instruction {
    data class UpdateUser(val user: User) : Instruction
    object Default : Instruction
    object Loading : Instruction
    data class ShowMessage(val message: String) : Instruction
}