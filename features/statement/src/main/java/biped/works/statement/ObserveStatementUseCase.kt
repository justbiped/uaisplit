package biped.works.statement

import biped.works.statement.data.Statement
import biped.works.statement.data.TimeSpan
import biped.works.statement.data.Transaction
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class ObserveStatementUseCase @Inject constructor(

) {
    operator fun invoke() = flowOf(
        Statement(
            balance = "",
            transactions = listOf(
                Transaction(
                    id = "myId",
                    value = 1500.50,
                    description = "Rent of the trash car"
                )
            ),
            timeSpan = TimeSpan("", "")
        )
    )
}