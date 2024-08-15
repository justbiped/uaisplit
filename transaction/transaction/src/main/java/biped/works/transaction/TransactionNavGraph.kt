package biped.works.transaction

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.transaction.ui.TransactionScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.transactionNavGraph(navController: NavHostController) {
    navigation<TransactionGraph>(startDestination = TransactionRoute("")) {
        composable<TransactionRoute> { backStackEntry ->
            TransactionScreen(
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}

@Serializable
object TransactionGraph

@Serializable
data class TransactionRoute(val id: String)