package biped.works.statement

import biped.works.statement.data.Transaction
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

class ObserveTransactionsUseCase @Inject constructor() {

    operator fun invoke() = flowOf(
        listOf(
            Transaction(
                id = "myId",
                value = 1500.50,
                description = "Rent of the trash car"
            )
        )
    )
}