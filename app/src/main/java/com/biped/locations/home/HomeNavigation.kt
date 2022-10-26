package com.biped.locations.home

import StatementNavGraph
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.biped.locations.R
import com.biped.locations.user.ProfileNavGraph
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
        graph = StatementNavGraph.route,
        route = StatementNavGraph.StatementDirection.route,
        title = R.string.statement_list_label,
        unselectedIcon = Icons.Outlined.ListAlt,
        selectedIcon = Icons.Filled.ListAlt
    )

    object UserSettings : HomeDestination(
        graph = ProfileNavGraph.route,
        route = ProfileNavGraph.SettingsDirection.route,
        title = R.string.profile_destination_label,
        unselectedIcon = Icons.Outlined.People,
        selectedIcon = Icons.Filled.People
    )

    companion object {
        val homeDestinationsSet = hashSetOf(
            StatementNavGraph.route,
            StatementNavGraph.StatementDirection.route,
            ProfileNavGraph.SettingsDirection.route,
            ProfileNavGraph.route
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = HomeDestination.StatementGraph.graph) {
        statementGraph(navController)
        profileNavGraph(navController)
    }
}
