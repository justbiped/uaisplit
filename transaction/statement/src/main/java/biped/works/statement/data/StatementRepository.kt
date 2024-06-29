package biped.works.statement.data

import biped.works.statement.data.remote.StatementApi
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class StatementRepository @Inject constructor(
    private val statementApi: StatementApi
) {
    val statementStream = flow {
        emit(statementApi.getStatement().toDomain())
    }
}