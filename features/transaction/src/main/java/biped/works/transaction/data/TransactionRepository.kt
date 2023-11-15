package biped.works.transaction.data

import biped.works.transaction.data.remote.TransactionApi
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionApi: TransactionApi
) {
    fun listTransactions(): List<Transaction> {
        return transactionApi.fetchTransactions()
    }

}