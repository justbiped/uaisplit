package com.biped.locations.user

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.biped.locations.user.profile.ui.ProfileScreen
import com.biped.locations.user.settings.ui.UserSettingsScreen
import com.favoriteplaces.core.compose.Direction
import com.favoriteplaces.core.compose.NavDestination
import com.favoriteplaces.core.compose.composable

sealed class ProfileNavGraph {
    companion object : NavDestination(route = "profile_graph_route")

    object SettingsDirection : NavDestination(route = "user_settings_route")

    data class ProfileDirection(val userId: String) : Direction {
        override val route: String = "$ROOT/$userId"
        override val popUpRoute: String = SettingsDirection.route

        companion object {
            const val USER_ID_ARG = "userID"
            const val ROOT = "user_profile_route"

            val destination = NavDestination(
                route = "$ROOT/{$USER_ID_ARG}",
                arguments = listOf(navArgument(USER_ID_ARG) { type = NavType.StringType })
            )
        }
    }
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileNavGraph.SettingsDirection.route,
        route = ProfileNavGraph.route
    ) {
        composable(ProfileNavGraph.SettingsDirection) {
            UserSettingsScreen(viewModel = hiltViewModel(), navController = navController)
        }

        composable(ProfileNavGraph.ProfileDirection.destination) { backStackEntry ->
            ProfileScreen(
                viewModel = hiltViewModel(),
                userId = backStackEntry.arguments
                    ?.getString(ProfileNavGraph.ProfileDirection.USER_ID_ARG)
                    .orEmpty()
            )
        }
    }
}