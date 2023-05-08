package com.biped.works.profile.ui

//internal sealed interface Instruction {
//    data class UpdateProfile(
//        val profile: ProfileUiModel = ProfileUiModel(),
//        val isLoading: Boolean = false
//    ) : Instruction
//
//    object ProfileSaved : Instruction
//}
//
//internal fun MutableInstructionFlow<Instruction>.update(
//    action: Instruction.UpdateProfile.() -> Instruction.UpdateProfile
//) {
//    (value as? Instruction.UpdateProfile)?.also { value = action(it) }
//}