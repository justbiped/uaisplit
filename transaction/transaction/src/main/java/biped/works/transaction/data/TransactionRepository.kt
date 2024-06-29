package biped.works.transaction.data

import biped.works.transaction.data.remote.TransactionApi
import kotlinx.coroutines.flow.emptyFlow

class TransactionRepository constructor(transactionApi: TransactionApi) {

    //fun createTransaction()

    fun transactionStream(id: String) = emptyFlow<String>()

}