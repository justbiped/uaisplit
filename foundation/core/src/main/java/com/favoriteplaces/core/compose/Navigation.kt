package com.favoriteplaces.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.composable

open class NavDestination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList()
)

fun NavGraphBuilder.composable(
    destination: NavDestination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.route,
        arguments = destination.arguments,
        deepLinks = destination.deepLinks,
        content = content
    )
}

fun NavController.navigate(
    direction: Direction,
    routeOptions: NavOptionsBuilder.() -> Unit = {},
    popUpOptions: PopUpToBuilder.() -> Unit = {}
) {
    navigate(direction.route) {
        routeOptions()
        popUpTo(direction.route, popUpToBuilder = popUpOptions)
    }
}

val NavController.currentRouteState: State<String>
    @Composable get() = produceState("", this) {
        currentBackStackEntryFlow.collect {
            value = it.destination.route.orEmpty()
        }
    }

interface Direction {
    val route: String
    val popUpRoute: String
}