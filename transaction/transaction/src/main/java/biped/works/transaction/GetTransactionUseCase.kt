package biped.works.transaction

import biped.works.transaction.data.Transaction
import biped.works.transaction.data.TransactionRepository
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(id: String): Result<Transaction> {
        try {
            val transaction = transactionRepository.getTransaction(id)
            return Result.success(transaction)
        } catch (error: Throwable) {
            return Result.failure(error)
        }
    }
}