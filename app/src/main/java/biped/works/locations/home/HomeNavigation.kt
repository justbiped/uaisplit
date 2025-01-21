package biped.works.locations.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import biped.works.compose.animtation.LocalSharedTransitionScope
import biped.works.statement.StatementGraph
import biped.works.statement.statementNavGraph
import com.biped.works.settings.SettingsGraph
import com.biped.works.settings.settingsNavGraph

object HomeNavigation {
    val destinations = setOf(
        StatementGraph,
        SettingsGraph
    )

    val start = destinations.first()

    fun contains(destination: Any?): Boolean = destinations.contains(destination)

    fun getDestination(route: String): Any? =
        destinations.firstOrNull { it.startDestination::class.qualifiedName == route }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {
            NavHost(navController, startDestination = HomeNavigation.start::class) {
                statementNavGraph(navController)
                settingsNavGraph(navController)
            }
        }
    }
}