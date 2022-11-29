package biped.works.transaction.ui

internal sealed interface Instruction {
    object Default : Instruction
    object Loading : Instruction
}