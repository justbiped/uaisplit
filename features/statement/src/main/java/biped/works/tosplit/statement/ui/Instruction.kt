package biped.works.tosplit.statement.ui

import biped.works.tosplit.statement.data.Transaction

internal sealed interface Instruction {
    object Default : Instruction
    object Loading : Instruction
    data class UpdateStatement(val transactions: List<Transaction>) : Instruction
}
