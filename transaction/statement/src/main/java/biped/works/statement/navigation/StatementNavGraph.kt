package biped.works.statement.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import biped.works.compose.navigation.NavGraph
import biped.works.statement.ui.StatementScreen
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.statementNavGraph(
    navController: NavHostController,
    transitionScope: SharedTransitionScope
) {
    navigation<StatementGraph>(
        startDestination = StatementGraph.startDestination
    ) {
        composable<StatementScreenDestination> {
            StatementScreen(viewModel = hiltViewModel())
        }
    }
}

@Serializable
object StatementGraph : NavGraph {
    override val startDestination = StatementScreenDestination
}

@Serializable
object StatementScreenDestination
