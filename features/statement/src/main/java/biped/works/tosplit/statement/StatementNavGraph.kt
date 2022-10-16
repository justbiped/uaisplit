import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import biped.works.tosplit.statement.ui.StatementScreen
import com.favoriteplaces.core.compose.NavDestination
import com.favoriteplaces.core.compose.composable

sealed interface StatementDirections {
    object Graph : NavDestination("statement_graph_route")
    object Statement : NavDestination("statement_route")
}

fun NavGraphBuilder.statementGraph(navController: NavController) {
    navigation(
        startDestination = StatementDirections.Statement.route,
        route = StatementDirections.Graph.route
    ) {
        composable(StatementDirections.Statement) {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}