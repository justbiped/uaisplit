package com.biped.locations.home

import StatementDirections
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.biped.locations.R
import com.biped.locations.user.ProfileDestinations
import com.biped.locations.user.profileNavGraph
import statementGraph

sealed class HomeDestination(
    val graph: String,
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    object StatementGraph : HomeDestination(
        graph = StatementDirections.Graph.route,
        route = StatementDirections.Statement.route,
        title = R.string.statement_list_label,
        unselectedIcon = Icons.Outlined.ViewList,
        selectedIcon = Icons.Filled.ViewList
    )

    object UserSettings : HomeDestination(
        graph = ProfileDestinations.Graph.route,
        route = ProfileDestinations.Settings.route,
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HomeDestination.StatementGraph.graph) {
        statementGraph(navController)
        profileNavGraph(navController)
    }
}
