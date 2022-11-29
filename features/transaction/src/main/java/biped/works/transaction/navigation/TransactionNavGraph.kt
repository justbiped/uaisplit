package biped.works.transaction.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import biped.works.compose.NavDirection
import biped.works.compose.NavGraph
import biped.works.compose.composable
import biped.works.transaction.ui.TransactionsScreen

object TransactionNavGraph : NavGraph("transaction_nav_graph") {
    override val startDestination: String = Transactions.route

    internal object Transactions : NavDirection("transactions_route")
    internal object CreateTransaction : NavDirection("create_transaction_route")
}

fun NavGraphBuilder.transactionNavGraph(navController: NavHostController) {
    navigation(
        startDestination = TransactionNavGraph.startDestination,
        route = TransactionNavGraph.route
    ) {
        composable(TransactionNavGraph.Transactions) {
            TransactionsScreen(hiltViewModel())
        }

        composable(TransactionNavGraph.CreateTransaction) {
        }
    }
}