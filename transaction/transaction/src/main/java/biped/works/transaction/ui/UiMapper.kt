package biped.works.transaction.ui

import biped.works.transaction.data.Transaction

fun Transaction.toUiModel() = TransactionUiModel(
    id = id,
    owner = owner,
    name = name,
    description = description,
    due = due,
    amount = value.amount,
    currency = value.currency
)