package com.biped.works.settings

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.compose.navigation.NavGraph
import com.biped.works.profile.SharedAnimation
import com.biped.works.profile.profileNavGraph
import com.biped.works.settings.ui.UserSettingsScreen
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController,
    transitionScope: SharedTransitionScope
) {
    navigation<SettingsGraph>(
        startDestination = SettingsDestination
    ) {
        composable<SettingsDestination> {
            SharedAnimation(transitionScope) {
                UserSettingsScreen(
                    viewModel = hiltViewModel(),
                    navController = navController
                )
            }
        }
        profileNavGraph(navController, transitionScope)
    }
}

@Serializable
object SettingsGraph : NavGraph {
    override val startDestination = SettingsDestination
}

@Serializable
object SettingsDestination