package biped.works.transaction.ui

data class TransactionUiModel(
    val id: String = "",
    val owner: String = "",
    val name: String = "",
    val description: String = "",
    val due: String = "",
    val amount: Double = 0.0,
    val currency: String = ""
)