package biped.works.transaction

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.transaction.ui.TransactionScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.transactionNavGraph() {
    navigation<TransactionGraph>(startDestination = TransactionRoute::class) {
        composable<TransactionRoute> {
            TransactionScreen(viewModel = hiltViewModel())
        }
    }
}

@Serializable
object TransactionGraph

@Serializable
data class TransactionRoute(val id: String)