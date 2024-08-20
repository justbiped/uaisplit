package biped.works.statement.data

import biped.works.statement.data.remote.RemoteStatement
import biped.works.statement.data.remote.StatementApi
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class StatementRepository @Inject constructor(
    private val statementApi: StatementApi
) {

    suspend fun fetchStatement(timeSpan: TimeSpan): Statement {
        return statementApi
            .getStatement(timeSpan.entry, timeSpan.conclusion)
            .toDomain()
    }

}