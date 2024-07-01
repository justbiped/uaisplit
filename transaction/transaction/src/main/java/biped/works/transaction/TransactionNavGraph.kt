package biped.works.transaction

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import biped.works.transaction.ui.TransactionScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.transactionNavGraph() {
    navigation<TransactionGraph>(startDestination = TransactionRoute) {
        composable<TransactionRoute> { backstackEntry ->
            backstackEntry.toRoute<TransactionRoute>()
            TransactionScreen()
        }
    }
}

@Serializable
object TransactionGraph

@Serializable
object TransactionRoute