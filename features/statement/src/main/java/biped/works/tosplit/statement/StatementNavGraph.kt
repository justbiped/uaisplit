import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.tosplit.statement.ui.StatementScreen

sealed class StatementDirections(
    val route: String, arguments: List<NamedNavArgument> = emptyList()
) {
    object Graph : StatementDirections("statement_graph_route")
    object Statement: StatementDirections("statement_route")
}

fun NavGraphBuilder.statementGraph(navController: NavController) {
    navigation(
        startDestination = StatementDirections.Statement.route,
        route = StatementDirections.Graph.route
    ) {
        composable(StatementDirections.Statement.route) {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}