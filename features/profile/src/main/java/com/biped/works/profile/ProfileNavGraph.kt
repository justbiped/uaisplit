package com.biped.works.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import biped.works.compose.NavDirection
import biped.works.compose.NavGraph
import biped.works.compose.composable
import com.biped.works.profile.ui.ProfileScreen

object ProfileNavGraph : NavGraph("profile_graph_route") {
    override val startDestination: String get() = ProfileRoute.route

    internal object ProfileRoute :
        NavDirection(
            route = "user_profile_route",
            deepLinks = listOf(NavDeepLink("http://biped.works/profile"))
        )
}

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileNavGraph.startDestination,
        route = ProfileNavGraph.route
    ) {
        composable(ProfileNavGraph.ProfileRoute) {
            ProfileScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}