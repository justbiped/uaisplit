package com.biped.works.profile

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
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
) {
    navigation(
        route = ProfileGraph.route,
        startDestination = ProfileGraph.Profile.route
    ) {
        composable(
            route = ProfileGraph.Profile.route,
            deepLinks = listOf(ProfileGraph.Profile.deepLink)
        ) {
            SharedAnimation(transitionScope) {
                ProfileScreen(
                    viewModel = hiltViewModel(),
                    navController = navController
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
class SharedAnimationScope(
    private val sharedTransitionScope: SharedTransitionScope,
    val animatedContentScope: AnimatedContentScope,
) : SharedTransitionScope by sharedTransitionScope

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedContentScope.SharedAnimation(
    transitionScope: SharedTransitionScope,
    sharedAnimationScope: @Composable SharedAnimationScope.() -> Unit
) {
    SharedAnimationScope(transitionScope, this).sharedAnimationScope()
}