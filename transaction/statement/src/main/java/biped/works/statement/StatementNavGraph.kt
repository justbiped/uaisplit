package biped.works.statement

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import biped.works.compose.navigation.NavGraph
import biped.works.statement.ui.StatementScreen
import biped.works.transaction.transactionNavGraph
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.statementNavGraph(
    navController: NavHostController,
    transitionScope: SharedTransitionScope
) {
    navigation<StatementGraph>(
        startDestination = StatementRoute
    ) {
        composable<StatementRoute> {
            StatementScreen(
                viewModel = hiltViewModel(),
                onNavigate = { destination -> navController.navigate(destination) })
        }
        transactionNavGraph(navController)
    }
}

@Serializable
object StatementGraph : NavGraph {
    override val startDestination get() = StatementRoute
}

@Serializable
object StatementRoute