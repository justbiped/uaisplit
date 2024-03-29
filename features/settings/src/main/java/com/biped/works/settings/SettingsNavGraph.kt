package com.biped.works.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.compose.navigation.SettingsGraph
import com.biped.works.profile.profileNavGraph
import com.biped.works.settings.ui.UserSettingsScreen

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        route = SettingsGraph.route,
        startDestination = SettingsGraph.Settings.route
    ) {
        composable(
            route = SettingsGraph.Settings.route,
            deepLinks = listOf(SettingsGraph.Settings.deepLink)
        ) {
            UserSettingsScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        profileNavGraph(navController)
    }
}