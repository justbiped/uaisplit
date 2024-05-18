package com.biped.works.profile

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.compose.navigation.ProfileGraph
import com.biped.works.profile.ui.ProfileScreen

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    transitionScope: SharedTransitionScope
) = with(transitionScope) {
    navigation(
        route = ProfileGraph.route,
        startDestination = ProfileGraph.Profile.route
    ) {
        composable(
            route = ProfileGraph.Profile.route,
            deepLinks = listOf(ProfileGraph.Profile.deepLink)
        ) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                animatedScope = this,
                navController = navController
            )
        }
    }
}