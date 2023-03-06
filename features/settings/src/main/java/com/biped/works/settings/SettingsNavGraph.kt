package com.biped.works.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import biped.works.compose.NavDirection
import biped.works.compose.NavGraph
import biped.works.compose.composable
import com.biped.works.profile.profileNavGraph
import com.biped.works.settings.ui.UserSettingsScreen

object SettingsNavGraph : NavGraph("settings_nav_graph") {
    override val startDestination: String get() = SettingsRoute.route

    internal object SettingsRoute : NavDirection(
        route = "user_settings_route",
        deepLinks = listOf(NavDeepLink("http://biped.works/settings"))
    )
}

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsNavGraph.startDestination,
        route = SettingsNavGraph.route
    ) {
        composable(SettingsNavGraph.SettingsRoute) {
            UserSettingsScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        profileNavGraph(navController)
    }
}