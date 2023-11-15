package biped.works.transaction

import biped.works.transaction.data.TransactionRepository
import javax.inject.Inject

class ObserveTransactionsUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    operator fun invoke() = transactionRepository.listTransactions()
}