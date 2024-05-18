package biped.works.locations.home

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import biped.works.compose.navigation.SettingsGraph
import biped.works.compose.navigation.StatementGraph
import biped.works.locations.R
import biped.works.statement.navigation.transactionNavGraph
import com.biped.works.settings.settingsNavGraph

sealed class HomeDestination(
    val graph: String,
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    data object Statement : HomeDestination(
        graph = StatementGraph.route,
        route = StatementGraph.Statement.route,
        title = R.string.statement_destination_label,
        unselectedIcon = Icons.Outlined.ViewList,
        selectedIcon = Icons.Filled.ViewList
    )

    data object UserSettings : HomeDestination(
        graph = SettingsGraph.route,
        route = SettingsGraph.Settings.route,
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )

    companion object {
        private val homeDestinationsSet = hashSetOf(
            Statement.route,
            UserSettings.route
        )

        fun contains(route: String) = homeDestinationsSet.contains(route)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val transitionScope = this
        NavHost(navController, startDestination = HomeDestination.Statement.graph) {
            transactionNavGraph(navController, transitionScope)
            settingsNavGraph(navController, transitionScope = transitionScope)
        }
    }

}
