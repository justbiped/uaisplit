package biped.works.transaction.data

data class Transaction(
    val id: String,
    val owner: String,
    val name: String,
    val description: String,
    val due: String,
    val value: Amount
)

data class Amount(
    val amount: Double,
    val currency: String
)