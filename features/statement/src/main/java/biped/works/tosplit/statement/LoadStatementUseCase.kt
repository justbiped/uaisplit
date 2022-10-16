package biped.works.tosplit.statement

import biped.works.tosplit.statement.data.Statement
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class LoadStatementUseCase @Inject constructor() {

    operator fun invoke() = flowOf(
        listOf(
            Statement(
                id = "myId",
                value = 1500.50,
                description = "Rent of the trash car"
            )
        )
    )
}