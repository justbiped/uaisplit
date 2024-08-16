package biped.works.transaction.ui

import biped.works.transaction.data.Amount
import biped.works.transaction.data.Recurrence
import biped.works.transaction.data.Transaction
import biped.works.transaction.data.TransactionUpdate

fun Transaction.toUiModel() = TransactionUiModel(
    id = id,
    owner = owner,
    name = name,
    description = description,
    due = due,
    amount = value.amount,
    currency = value.currency
)

fun TransactionUiModel.toTransactionUpdate() = TransactionUpdate(
    id = null,
    owner = owner,
    name = name,
    description = description,
    due = "2024-08-20",
    value = Amount(200.00, "BRL"),
    recurrence = Recurrence("times=1", "MONTHLY")
)