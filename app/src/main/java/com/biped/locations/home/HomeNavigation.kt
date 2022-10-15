package com.biped.locations.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.biped.locations.R
import com.biped.locations.user.settings.ui.UserSettingsScreen

sealed class HomeDestination(
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    object LocationList : HomeDestination(
        route = "location_list_route",
        title = R.string.location_list_label,
        unselectedIcon = Icons.Outlined.ViewList,
        selectedIcon = Icons.Filled.ViewList
    )

    object UserSettings : HomeDestination(
        route = "user_settings__route",
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HomeDestination.LocationList.route) {
        composable(HomeDestination.LocationList.route) {
          //  StatementScreen()
        }
        composable(HomeDestination.UserSettings.route) {
            UserSettingsScreen(viewModel = hiltViewModel())
        }
    }
}
