package biped.works.locations.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import biped.works.compose.LocalWindow
import biped.works.compose.LocalWindowProvider
import biped.works.compose.collectWithLifecycle
import biped.works.compose.setStatusBarContrast
import biped.works.locations.home.ui.HomeScreen
import com.biped.locations.theme.CashTheme
import com.biped.works.settings.data.ThemeSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var themeSettings by mutableStateOf(ThemeSettings())
        setContent {
            window.isNavigationBarContrastEnforced = false
            navController = rememberNavController()

            viewModel.instruction.collectWithLifecycle { instruction ->
                if (instruction is Instruction.UpdateTheme) {
                    themeSettings = instruction.themeSettings
                }
            }

            LocalWindowProvider(window) {
                CashTheme(
                    colorTheme = themeSettings.colorScheme,
                    useDynamicColors = themeSettings.useDynamicColors
                ) {
                    LocalWindow.current?.setStatusBarContrast(MaterialTheme.colorScheme.background)
                    HomeScreen()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)
    }
}

