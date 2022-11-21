package biped.works.locations.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import biped.works.test.compose.testNavController
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeComposeStateTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun `current route state updates on destination change`() {
        var currentRoute = ""
        composeTestRule.setContent {
            val navController = testNavController("first_destination", "second_destination")
            val homeState = HomeComposeState(navController)

            currentRoute = homeState.currentRoute
            navController.setCurrentDestination("second_destination")
        }

        assertThat(currentRoute).isEqualTo("second_destination")
    }

    @Test
    fun `show bottom nav bar when selected destination is a home menu destination`() {
        var showBottomBar = false

        composeTestRule.setContent {
            val navController = testNavController(homeDestinations)
            val state = HomeComposeState(navController)

            showBottomBar = state.showBottomBar
            state.navigate(HomeDestination.UserSettings)
        }

        assertThat(showBottomBar).isTrue()
    }

    @Test
    fun `hide bottom nav bar when selected destination is not a home menu destination`() {
        var showBottomBar = true

        composeTestRule.setContent {
            val navController = testNavController("statement_graph_route", "no_home_destination")
            val state = HomeComposeState(navController)

            showBottomBar = state.showBottomBar
            navController.setCurrentDestination("no_home_destination")
        }

        assertThat(showBottomBar).isFalse()
    }

    private val homeDestinations = listOf(
        HomeDestination.UserSettings.graph,
        HomeDestination.UserSettings.route,
        HomeDestination.StatementGraph.graph,
        HomeDestination.StatementGraph.route
    )
}