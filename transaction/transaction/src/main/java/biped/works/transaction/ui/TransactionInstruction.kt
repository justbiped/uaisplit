package biped.works.transaction.ui

import biped.works.coroutines.MutableUiStateFlow

internal sealed interface TransactionInstruction {
    object State : TransactionInstruction

    object FailedToUpdate : TransactionInstruction
}

internal fun MutableUiStateFlow<TransactionInstruction>.updateState(
    action: TransactionInstruction.State.() -> TransactionInstruction.State
) {
    (value as? TransactionInstruction.State)?.also { value = action(it) }
}