package com.biped.works.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.compose.animtation.LocalAnimatedVisibilityProvider
import biped.works.compose.navigation.ProfileGraph
import com.biped.works.profile.ui.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController
) {
    navigation(
        route = ProfileGraph.route,
        startDestination = ProfileGraph.Profile.route
    ) {
        composable(
            route = ProfileGraph.Profile.route,
            deepLinks = listOf(ProfileGraph.Profile.deepLink)
        ) {
            LocalAnimatedVisibilityProvider {
                ProfileScreen(
                    viewModel = hiltViewModel(),
                    navController = navController
                )
            }
        }
    }
}