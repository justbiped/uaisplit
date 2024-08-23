package biped.works.statement.ui

import biped.works.coroutines.MutableUiStateFlow
import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import biped.works.transaction.TransactionRoute

internal sealed interface StatementInstruction {
    data class State(
        val uiModel: Statement = Statement("", emptyList(), TimeSpan("", "")),
        val isLoading: Boolean = true
    ) : StatementInstruction {
        val isEmpty get() = uiModel.transactions.isEmpty()
    }

    data class OpenTransaction(private val id: String) : StatementInstruction {
        val destination = TransactionRoute(id)
    }

    data object AddTransaction : StatementInstruction {
        val destination = TransactionRoute("")
    }

    data object FailedToFetchStatement : StatementInstruction
}

internal fun MutableUiStateFlow<StatementInstruction>.updateState(
    action: StatementInstruction.State.() -> StatementInstruction.State
) {
    (value as? StatementInstruction.State)?.also { value = action(it) }
}