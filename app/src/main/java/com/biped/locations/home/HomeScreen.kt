package com.biped.locations.home

import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.biped.locations.settings.ui.ThemeSettingsUiModel
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.components.LargeLabel
import com.biped.locations.user.settings.ui.rememberState

private data class HomeComposeState(
    val themeSettings: ThemeSettingsUiModel = ThemeSettingsUiModel()
) {
    fun updateTheme(themeSettings: ThemeSettingsUiModel) = copy(themeSettings = themeSettings)
    fun toDefault() = copy(themeSettings = ThemeSettingsUiModel())
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    var state by rememberState(state = HomeComposeState())

    state = when (val instruction = viewModel.collectInstruction()) {
        is HomeInstruction.UpdateTheme -> state.updateTheme(instruction.themeSettings)
        is HomeInstruction.Default -> state.toDefault()
    }

    HomeScreenStateless(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenStateless(state: HomeComposeState) {
    val navController = rememberNavController()
    AppTheme(state.themeSettings.colorScheme, state.themeSettings.useDynamicColors) {
        Scaffold(
            bottomBar = { BottomNavigation(navController) }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenStateless(HomeComposeState())
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val homeDestinations = listOf(
        HomeDestination.LocationList,
        HomeDestination.UserSettings
    )

    fun navigate(route: String) {
        navController.navigate(route) {
            popUpTo(HomeDestination.LocationList.route) { saveState = true }
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
                onClick = { navigate(destination.route) },
                icon = { Icon(icon, contentDescription = "") },
                label = { LargeLabel(text = stringResource(id = destination.title)) }
            )
        }
    }
}

@Composable
fun LocationListScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {}
}

val NavBackStackEntry.currentRoute: String get() = destination.route ?: ""

@Composable
private fun HomeViewModel.collectInstruction() =
    instruction.collectAsState(initial = HomeInstruction.Default).value

