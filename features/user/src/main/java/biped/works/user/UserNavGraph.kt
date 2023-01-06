package biped.works.user

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import biped.works.compose.Destination
import biped.works.compose.NavDirection
import biped.works.compose.NavGraph
import biped.works.compose.composable
import biped.works.user.ProfileDestination.Companion.ROUTE
import biped.works.user.profile.ui.ProfileScreen
import biped.works.user.settings.ui.UserSettingsScreen

object ProfileNavGraph : NavGraph("profile_graph_route") {
    override val startDestination: String get() = SettingsRoute.route

    internal object SettingsRoute : NavDirection("user_settings_route")
    internal object ProfileRoute : NavDirection(ROUTE)
}

internal data class ProfileDestination(private val userId: String) : Destination {
    override val route: String get() = "$HOST/$userId"
    override val popUpRoute: String = ProfileNavGraph.SettingsRoute.route

    companion object {
        private const val HOST = "user_profile_route"
        const val USER_ID_ARG = "useId"
        const val ROUTE = "$HOST/{$USER_ID_ARG}"
    }
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileNavGraph.startDestination,
        route = ProfileNavGraph.route
    ) {
        composable(ProfileNavGraph.SettingsRoute) {
            UserSettingsScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(ProfileNavGraph.ProfileRoute) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}