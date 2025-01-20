package biped.works.statement.ui

import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import biped.works.transaction.TransactionRoute

data class StatementState(
    val uiModel: Statement = Statement("", emptyList(), TimeSpan("", "")),
    val isLoading: Boolean = true
) {
    val isEmpty get() = uiModel.transactions.isEmpty()
}

internal sealed interface StatementEvent {
    data class OpenTransaction(private val id: String) : StatementEvent {
        val destination = TransactionRoute(id)
    }

    data object AddTransaction : StatementEvent {
        val destination = TransactionRoute("")
    }

    data object FailedToFetchStatement : StatementEvent
}