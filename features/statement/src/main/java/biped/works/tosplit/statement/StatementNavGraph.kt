import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import biped.works.tosplit.statement.ui.StatementScreen
import com.favoriteplaces.core.compose.NavDestination
import com.favoriteplaces.core.compose.composable

sealed interface StatementNavGraph {
    companion object : NavDestination("statement_graph_route")
    object StatementDirection : NavDestination("statement_route")
}

fun NavGraphBuilder.statementGraph(navController: NavController) {
    navigation(
        startDestination = StatementNavGraph.StatementDirection.route,
        route = StatementNavGraph.route
    ) {
        composable(StatementNavGraph.StatementDirection) {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}