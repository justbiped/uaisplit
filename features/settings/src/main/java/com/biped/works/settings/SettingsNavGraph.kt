package com.biped.works.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import biped.works.compose.animtation.LocalAnimatedVisibilityProvider
import biped.works.compose.navigation.NavGraph
import com.biped.works.profile.profileNavGraph
import com.biped.works.settings.ui.UserSettingsScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController
) {
    navigation<SettingsGraph>(
        startDestination = SettingsDestination
    ) {
        composable<SettingsDestination> {
            LocalAnimatedVisibilityProvider {
                UserSettingsScreen(
                    viewModel = hiltViewModel(),
                    navController = navController
                )
            }
        }
    }
    profileNavGraph(navController)
}

@Serializable
object SettingsGraph : NavGraph {
    override val startDestination = SettingsDestination
}

@Serializable
object SettingsDestination