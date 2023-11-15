package biped.works.transaction.data

import biped.works.transaction.data.remote.TransactionApi
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TransactionRepository @Inject constructor(
    private val transactionApi: TransactionApi
) {
    suspend fun listTransactions(): List<Transaction> {
        return transactionApi.fetchTransactions().toDomain()
    }
}