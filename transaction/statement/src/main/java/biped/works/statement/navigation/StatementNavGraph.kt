package biped.works.statement.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import biped.works.compose.navigation.StatementGraph
import biped.works.statement.ui.StatementScreen

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.statementNavGraph(
    navController: NavHostController,
    transitionScope: SharedTransitionScope
) {
    navigation(
        route = StatementGraph.route,
        startDestination = StatementGraph.Statement.route
    ) {
        composable(route = StatementGraph.Statement.route) {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}