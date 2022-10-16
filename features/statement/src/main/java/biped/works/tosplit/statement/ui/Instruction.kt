package biped.works.tosplit.statement.ui

import biped.works.tosplit.statement.data.Statement

internal sealed interface Instruction {
    object Default : Instruction
    object Loading : Instruction
    data class ShowStatement(val statement: List<Statement>) : Instruction
}
