package biped.works.statement.ui

import biped.works.coroutines.MutableUiStateFlow
import biped.works.statement.data.Statement

internal sealed interface StatementInstruction {
    data class State(
        val uiModel: Statement = Statement(emptyList()),
        val isLoading: Boolean = false
    ) : StatementInstruction

    object FailedToFetchStatement : StatementInstruction
}

internal fun MutableUiStateFlow<StatementInstruction>.updateState(
    action: StatementInstruction.State.() -> StatementInstruction.State
) {
    (value as? StatementInstruction.State)?.also { value = action(it) }
}