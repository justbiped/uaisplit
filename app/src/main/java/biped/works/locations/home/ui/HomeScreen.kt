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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import biped.works.compose.animtation.LocalPadding
import biped.works.compose.collectWithLifecycle
import biped.works.compose.navigation.currentRouteState
import biped.works.locations.home.HomeNavigation
import biped.works.locations.home.NavigationGraph
import biped.works.locations.main.BottomNavItem
import biped.works.locations.main.SettingsNavItem
import biped.works.locations.main.StatementNavItem
import biped.works.statement.StatementGraph
import com.biped.locations.theme.CashTheme
import com.biped.works.settings.SettingsGraph

@Stable
internal data class HomeState(
    val navController: NavHostController
) {
    val currentDestination: Any?
        @Composable get() {
            val currentRoute = navController.currentRouteState.value
            return HomeNavigation.getDestination(currentRoute)
        }

    val showBottomBar: Boolean @Composable get() = HomeNavigation.contains(currentDestination)

    fun default() {}

    fun navigate(destination: Any) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
private fun rememberHomeState(
    navController: NavHostController = rememberNavController()
) = remember { mutableStateOf(HomeState(navController)) }

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val state by rememberHomeState()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is HomeInstruction.Navigate -> state.navigate(instruction.destination)
            is HomeInstruction.Default -> state.default()
        }
    }

    HomeScreenUi(state = state, onRouteSelected = { viewModel.selectHomeDestination(it) })
}

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private fun HomeScreenUi(
    state: HomeState,
    onRouteSelected: (destination: Any) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = state.showBottomBar,
                enter = slideInVertically(spring()) { it },
                exit = shrinkVertically() + slideOutVertically { it },
            ) {
                BottomNavigation(
                    currentRoute = state.currentDestination,
                    onSelectDestination = { onRouteSelected(it) })
            }
        }
    ) { innerPadding ->
        LocalPadding(innerPadding) {
            Column {
                NavigationGraph(navController = state.navController)
            }
        }
    }
}

@Composable
fun BottomNavigation(
    currentRoute: Any?, onSelectDestination: (destination: Any) -> Unit = {}
) {
    NavigationBar {
        HomeNavigation.destinations.forEach { destination ->
            val navItem = getMenuNavItem(destination)
            val isSelected = destination == currentRoute
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CashTheme {
        HomeScreenUi(rememberHomeState().value)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    CashTheme {
        BottomNavigation(currentRoute = HomeNavigation.destinations.first())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomBarPreview_Dark() {
    BottomBarPreview()
}