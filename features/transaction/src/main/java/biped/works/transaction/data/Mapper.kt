package biped.works.transaction.data

import biped.works.transaction.data.remote.TransactionRemote

fun TransactionRemote.toDomain() = Transaction(
    id = id,
    description = description,
    value = value
)

fun List<TransactionRemote>.toDomain() = map { remote -> remote.toDomain() }