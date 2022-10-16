package com.biped.locations.user

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

import androidx.navigation.navigation
import com.biped.locations.user.settings.ui.UserSettingsScreen
import com.favoriteplaces.core.compose.NavDestination
import com.favoriteplaces.core.compose.composable

sealed interface ProfileDestinations {
    object Graph : NavDestination("profile_graph_route")
    object Settings : NavDestination("user_settings_route")
    object Profile : NavDestination("user_profile_route")
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileDestinations.Settings.route,
        route = ProfileDestinations.Graph.route
    ) {
        composable(ProfileDestinations.Settings) {
            UserSettingsScreen(viewModel = hiltViewModel())
        }
    }
}