package biped.works.statement.data

import biped.works.statement.data.remote.RemoteStatement
import biped.works.statement.data.remote.RemoteTimeSpan
import biped.works.statement.data.remote.RemoteTransaction

fun RemoteStatement.toDomain() = Statement(
    balance = balance,
    transactions = transactions.toDomain(),
    timeSpan = timeSpan.toDomain()
)

fun List<RemoteTransaction>.toDomain() = map { it.toDomain() }

fun RemoteTransaction.toDomain() = Transaction(
    id = id,
    description = description,
    value = value
)

fun RemoteTimeSpan.toDomain() = TimeSpan(
    entry = entry,
    conclusion = conclusion
)