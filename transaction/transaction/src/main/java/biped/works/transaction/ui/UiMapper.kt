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
    amount = value.amount.toString(),
    currency = value.currency
)

fun TransactionUiModel.toTransactionUpdate() = TransactionUpdate(
    id = if (id.isNotBlank()) id else null,
    owner = owner,
    name = name,
    description = description,
    due = due,
    value = Amount(amount.toDouble(), currency),
    recurrence = Recurrence("times=1", "MONTHLY")
)