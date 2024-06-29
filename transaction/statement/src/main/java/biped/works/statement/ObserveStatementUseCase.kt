package biped.works.statement

import android.util.Log
import biped.works.statement.data.StatementRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

class ObserveStatementUseCase @Inject constructor(
    private val statementRepository: StatementRepository
) {
    operator fun invoke() = statementRepository
        .statementStream
        .onEach {
            print(it)
        }
        .catch {
            Log.d("statement", it.message?:"")
        }
}