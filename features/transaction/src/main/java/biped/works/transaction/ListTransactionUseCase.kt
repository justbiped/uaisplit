package biped.works.transaction

import biped.works.transaction.data.TransactionRepository
import javax.inject.Inject

class ListTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke() = transactionRepository.listTransactions()
}