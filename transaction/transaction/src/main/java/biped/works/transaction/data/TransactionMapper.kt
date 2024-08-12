package biped.works.transaction.data

import biped.works.transaction.data.remote.RemoteTransaction

fun RemoteTransaction.toDomain() = Transaction(
    id = id,
    owner = owner,
    name = name,
    description = description,
    due = due,
    value = Amount(value.amount, value.currency)
)

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