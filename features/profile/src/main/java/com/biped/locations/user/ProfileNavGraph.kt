package com.biped.locations.user

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.biped.locations.user.ProfileDestination.Companion.ROUTE
import com.biped.locations.user.profile.ui.ProfileScreen
import com.biped.locations.user.settings.ui.UserSettingsScreen
import com.favoriteplaces.core.compose.Destination
import com.favoriteplaces.core.compose.NavDirection
import com.favoriteplaces.core.compose.composable

object ProfileNavGraph : NavDirection("profile_graph_route") {
    val startDestination: String get() = SettingsRoute.route

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
            UserSettingsScreen(viewModel = hiltViewModel(), navController = navController)
        }

        composable(ProfileNavGraph.ProfileRoute) {
            ProfileScreen(viewModel = hiltViewModel())
        }
    }
}