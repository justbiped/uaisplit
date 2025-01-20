package biped.works.transaction.ui

data class TransactionState(
    val isLoading: Boolean = false,
    val uiModel: TransactionUiModel = TransactionUiModel()
)

sealed interface TransactionEvent {
    data object FailedToUpdate : TransactionEvent
}