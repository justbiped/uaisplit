package biped.works.locations.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import biped.works.statement.navigation.StatementGraph
import biped.works.statement.navigation.statementNavGraph
import com.biped.works.settings.SettingsGraph
import com.biped.works.settings.settingsNavGraph

object HomeNavigation {
    val destinations = hashSetOf(
        StatementGraph,
        SettingsGraph
    )

    fun contains(destination: Any?): Boolean = destinations.contains(destination)

    fun getDestination(route: String): Any? =
        destinations.firstOrNull { it.startDestination::class.qualifiedName == route }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val transitionScope = this
        NavHost(navController, startDestination = HomeNavigation.destinations.first()) {
            statementNavGraph(navController, transitionScope)
            settingsNavGraph(navController, transitionScope = transitionScope)
        }
    }
}