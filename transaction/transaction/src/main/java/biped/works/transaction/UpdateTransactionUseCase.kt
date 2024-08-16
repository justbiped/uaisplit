package biped.works.transaction

import biped.works.transaction.data.TransactionRepository
import biped.works.transaction.data.TransactionUpdate
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transaction: TransactionUpdate) {
        transactionRepository.saveTransaction(transaction)
    }
}
