package biped.works.test.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController

fun NavController.testNavGraph(destinations: List<String>): NavGraph {
    if (destinations.isEmpty()) throw IllegalArgumentException("Destination cannot be empty")

    return createGraph(startDestination = destinations.first()) {
        destinations.forEach { composable(it) {} }
    }
}

@Composable
fun testNavController(vararg destination: String): TestNavHostController {
    return testNavController { testNavGraph(destination.asList()) }
}

@Composable
fun testNavController(destinations: List<String>): TestNavHostController {
    return testNavController { testNavGraph(destinations) }
}

@Composable
fun testNavController(graphBuilder: NavController.() -> NavGraph): TestNavHostController {
    return TestNavHostController(LocalContext.current).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
        graph = graphBuilder()
    }
}
