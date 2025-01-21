package biped.works.locations.home.ui

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import biped.works.compose.animtation.LocalPadding
import biped.works.compose.collectWithLifecycle
import biped.works.locations.home.HomeNavigation
import biped.works.locations.home.NavigationGraph
import biped.works.locations.main.BottomNavItem
import biped.works.locations.main.SettingsNavItem
import biped.works.locations.main.StatementNavItem
import biped.works.statement.StatementGraph
import com.biped.locations.theme.CashTheme
import com.biped.works.settings.SettingsGraph

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val mainNavController = rememberNavController()
    val currentDestination by mainNavController.currentDestinationState()

    viewModel.uiEvent.collectWithLifecycle { event ->
        when (event) {
            is HomeEvent.Navigate -> mainNavController.navigateTo(event.destination)
        }
    }

    HomeScreenUi(
        currentDestination = currentDestination,
        navigationGraph = { NavigationGraph(mainNavController) },
        onRouteSelected = { viewModel.selectHomeDestination(it) })
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun HomeScreenUi(
    currentDestination: Any?,
    navigationGraph: @Composable () -> Unit,
    onRouteSelected: (Any) -> Unit
) {
    val showBottomBar = currentDestination != null
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(spring()) { it },
                exit = shrinkVertically() + slideOutVertically { it },
            ) {
                BottomNavigation(
                    currentDestination = currentDestination,
                    onSelectDestination = { onRouteSelected(it) })
            }
        }
    ) { innerPadding ->
        LocalPadding(innerPadding) {
            Column {
                navigationGraph()
            }
        }
    }
}

@Composable
fun BottomNavigation(
    currentDestination: Any?,
    onSelectDestination: (destination: Any) -> Unit = {}
) {
    NavigationBar {
        HomeNavigation.destinations.forEach { destination ->
            val navItem = getMenuNavItem(destination)
            val isSelected = destination == currentDestination
            val icon = if (isSelected) navItem.selectedIcon else navItem.unselectedIcon

            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelectDestination(destination) },
                icon = { Icon(icon, contentDescription = "") },
                label = { Text(text = stringResource(id = navItem.title)) })
        }
    }
}

private fun getMenuNavItem(destination: Any): BottomNavItem {
    return when (destination) {
        is StatementGraph -> StatementNavItem
        is SettingsGraph -> SettingsNavItem
        else -> StatementNavItem
    }
}

@Composable
private fun NavHostController.currentDestinationState() = produceState<Any?>(HomeNavigation.start) {
    currentBackStackEntryFlow.collect { backStack ->
        val route = backStack.destination.route.orEmpty()
        value = HomeNavigation.getDestination(route)
    }
}

private fun NavHostController.navigateTo(destination: Any) {
    navigate(destination) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CashTheme {
        HomeScreenUi(HomeNavigation.start, {}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    CashTheme {
        BottomNavigation(currentDestination = HomeNavigation.destinations.first())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomBarPreview_Dark() {
    BottomBarPreview()
}