package biped.works.statement.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import biped.works.compose.navigation.StatementGraph
import biped.works.statement.ui.TransactionsScreen

fun NavGraphBuilder.transactionNavGraph(navController: NavHostController) {
    navigation(
        route = StatementGraph.route,
        startDestination = StatementGraph.Statement.route
    ) {
        composable(route = StatementGraph.Statement.route) {
            TransactionsScreen(hiltViewModel())
        }
    }
}