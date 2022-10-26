package com.biped.locations.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.biped.locations.settings.ThemeSettings
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.components.LargeLabel
import com.favoriteplaces.core.compose.collectWithLifecycle
import com.favoriteplaces.core.compose.currentRouteState

@Stable
private data class HomeComposeState(
    val navController: NavHostController
) {
    var themeSettings: ThemeSettings by mutableStateOf(ThemeSettings())
        private set

    val currentRoute: String
        @Composable get() = navController.currentRouteState.value

    val showBottomBar: Boolean
        @Composable get() = HomeDestination.homeDestinationsSet.contains(currentRoute)

    fun updateTheme(settings: ThemeSettings) {
        themeSettings = settings
    }

    fun default() {
        themeSettings = ThemeSettings()
    }

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

}

@Composable
private fun rememberHomeState(
    navController: NavHostController = rememberNavController()
) = remember { mutableStateOf(HomeComposeState(navController)) }

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val state by rememberHomeState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is HomeInstruction.UpdateTheme -> state.updateTheme(instruction.themeSettings)
            is HomeInstruction.Navigate -> state.navigate(instruction.route)
            is HomeInstruction.Default -> state.default()
        }
    }

    HomeScreenUi(
        state = state,
        onRouteSelected = { viewModel.routeChanged(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenUi(
    state: HomeComposeState,
    onRouteSelected: (route: String) -> Unit = {}
) {
    AppTheme(
        state.themeSettings.colorScheme,
        state.themeSettings.useDynamicColors
    ) {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = state.showBottomBar,
                    enter = slideInVertically(tween()) { it },
                    exit = shrinkVertically() + slideOutVertically { it },
                ) {
                    BottomNavigation(
                        currentRoute = state.currentRoute,
                        onSelectDestination = { onRouteSelected(it) }
                    )
                }
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController = state.navController)
            }
        }
    }
}

@Composable
fun BottomNavigation(currentRoute: String, onSelectDestination: (route: String) -> Unit) {
    val homeDestinations = listOf(
        HomeDestination.StatementGraph,
        HomeDestination.UserSettings
    )

    NavigationBar {
        homeDestinations.forEach { destination ->
            val isSelected = destination.route == currentRoute
            val icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon

            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelectDestination(destination.graph) },
                icon = { Icon(icon, contentDescription = "") },
                label = { LargeLabel(text = stringResource(id = destination.title)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreenUi(rememberHomeState().value)
    }
}