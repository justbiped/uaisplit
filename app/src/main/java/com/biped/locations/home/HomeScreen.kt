package com.biped.locations.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.biped.locations.settings.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.components.LargeLabel

private data class HomeComposeState(
    val themeSettings: ThemeSettingsUiModel = ThemeSettingsUiModel()
) {
    fun updateTheme(themeSettings: ThemeSettingsUiModel) = copy(themeSettings = themeSettings)
    fun default() = copy(themeSettings = ThemeSettingsUiModel())
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    var state by remember { (mutableStateOf(HomeComposeState())) }

    state = when (val instruction = viewModel.collectInstruction()) {
        is HomeInstruction.UpdateTheme -> state.updateTheme(instruction.themeSettings)
        is HomeInstruction.Default -> state.default()
    }

    val (colorScheme, useDynamicColors) = state.themeSettings
    AppTheme(colorScheme, useDynamicColors) {
        HomeScreenStateless(state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenStateless(state: HomeComposeState) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val homeDestinations = listOf(
        HomeDestination.StatementGraph,
        HomeDestination.UserSettings
    )

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    NavigationBar {
        homeDestinations.forEach { destination ->
            val isSelected = destination.route == navBackStackEntry?.currentRoute
            val icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon

            NavigationBarItem(
                selected = isSelected,
                onClick = { navigate(destination.graph) },
                icon = { Icon(icon, contentDescription = "") },
                label = { LargeLabel(text = stringResource(id = destination.title)) }
            )
        }
    }
}

val NavBackStackEntry.currentRoute: String get() = destination.route ?: ""

@Composable
private fun HomeViewModel.collectInstruction() =
    instruction.collectAsState(initial = HomeInstruction.Default).value

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreenStateless(HomeComposeState())
    }
}
