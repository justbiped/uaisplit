package biped.works.statement.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import biped.works.compose.navigation.TransactionGraph
import biped.works.statement.ui.TransactionsScreen

fun NavGraphBuilder.transactionNavGraph(navController: NavHostController) {
    navigation(
        route = TransactionGraph.route,
        startDestination = TransactionGraph.Transaction.route
    ) {
        composable(route = TransactionGraph.Transaction.route) {
            TransactionsScreen(hiltViewModel())
        }
    }
}