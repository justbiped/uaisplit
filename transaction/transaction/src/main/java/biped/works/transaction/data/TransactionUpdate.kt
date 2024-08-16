package biped.works.transaction.data

data class TransactionUpdate(
    val id: String?,
    val owner: String,
    val name: String,
    val description: String,
    val due: String,
    val value: Amount,
    val recurrence: Recurrence
)

data class Recurrence(val frequency: String, val type: String)
