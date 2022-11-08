import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import biped.works.tosplit.statement.ui.StatementScreen
import com.favoriteplaces.core.compose.NavDirection
import com.favoriteplaces.core.compose.composable

object StatementNavGraph : NavDirection("statement_graph_route") {
    val startDestination = StatementDirection.route

    internal object StatementDirection : NavDirection("statement_route")
}

fun NavGraphBuilder.statementGraph(navController: NavController) {
    navigation(
        startDestination = StatementNavGraph.startDestination,
        route = StatementNavGraph.route
    ) {
        composable(StatementNavGraph.StatementDirection) {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}