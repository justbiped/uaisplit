package biped.works.transaction.data

import biped.works.transaction.data.remote.RemoteRecurrence
import biped.works.transaction.data.remote.RemoteTransaction
import biped.works.transaction.data.remote.RemoteTransactionUpdate
import biped.works.transaction.data.remote.RemoteValue

fun RemoteTransaction.toDomain() = Transaction(
    id = id,
    owner = owner,
    name = name,
    description = description,
    due = due,
    value = Amount(value.amount, value.currency)
)

fun TransactionUpdate.toRemote() = RemoteTransactionUpdate(
    id = id,
    owner = owner,
    name = name,
    description = description,
    due = due,
    value = RemoteValue(value.amount, value.currency),
    recurrence = RemoteRecurrence(recurrence.frequency, recurrence.type)
)