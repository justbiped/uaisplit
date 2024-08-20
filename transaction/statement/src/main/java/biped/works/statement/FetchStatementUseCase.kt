package biped.works.statement

import biped.works.statement.data.Statement
import biped.works.statement.data.StatementRepository
import biped.works.statement.data.TimeSpan
import javax.inject.Inject
import timber.log.Timber

class FetchStatementUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {
    suspend operator fun invoke(timeSpan: TimeSpan): Result<Statement> {
        return try {
            val statement = statementRepository.fetchStatement(timeSpan)
            Result.success(statement)
        } catch (error: Throwable) {
            Timber.e(error)
            Result.failure(error)
        }
    }
}