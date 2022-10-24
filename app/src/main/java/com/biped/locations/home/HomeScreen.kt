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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.biped.locations.settings.ThemeSettings
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.components.LargeLabel
import com.favoriteplaces.core.compose.currentRouteState

private data class HomeComposeState(
    val themeSettings: ThemeSettings = ThemeSettings(),
    val showBottomBar: Boolean = true
) {
    fun updateTheme(themeSettings: ThemeSettings) = copy(themeSettings = themeSettings)
    fun navigate(route: String) = copy(showBottomBar = homeDestinations.contains(route))
    fun default() = copy(themeSettings = ThemeSettings())
}

private val homeDestinations = HomeDestination.homeDestinationsSet()

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    var state by remember { (mutableStateOf(HomeComposeState())) }

    LaunchedEffect(Unit) {
        viewModel.instruction.collect { instruction ->
            state = when (instruction) {
                is HomeInstruction.UpdateTheme -> state.updateTheme(instruction.themeSettings)
                is HomeInstruction.Default -> state.default()
            }
        }
    }

    fun onDestinationChanged(route: String) {
        state = state.navigate(route)
    }

    AppTheme(
        state.themeSettings.colorScheme,
        state.themeSettings.useDynamicColors
    ) {
        HomeScreenUi(
            state = state,
            onDestinationChanged = { onDestinationChanged(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenUi(
    state: HomeComposeState,
    onDestinationChanged: (route: String) -> Unit = {}
) {
    val navController = rememberNavController()
    val currentRoute by navController.currentRouteState

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    LaunchedEffect(key1 = currentRoute) {
        onDestinationChanged(currentRoute)
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = state.showBottomBar,
                enter = slideInVertically(tween()) { it },
                exit = shrinkVertically() + slideOutVertically { it },
            ) {
                BottomNavigation(
                    currentRoute = currentRoute,
                    onSelectDestination = { navigate(it) }
                )
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            NavigationGraph(navController = navController)
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
        HomeScreenUi(HomeComposeState())
    }
}
