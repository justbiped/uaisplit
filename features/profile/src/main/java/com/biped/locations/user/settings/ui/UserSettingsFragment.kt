package com.biped.locations.user.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.biped.locations.settings.COLOR_SCHEME
import com.biped.locations.settings.DYNAMIC_COLORS
import com.biped.locations.settings.settingsDataStore
import com.biped.locations.theme.AppTheme
import com.biped.locations.theme.ColorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSettingsFragment : ComposeFragment() {

    private val viewModel: UserSettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUserSettings()
    }

    @Composable
    override fun Compose(savedInstanceState: Bundle?) {
        UserSettingsScreen(viewModel = viewModel)
    }
}

abstract class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (view as ComposeView).apply {
            setContent {
                var colorTheme by remember { mutableStateOf(ColorTheme.SYSTEM) }
                var useDynamicColors by remember { mutableStateOf(false) }

                LaunchedEffect("settings") {
                    context.settingsDataStore.data.collect { prefs ->
                        val colorSchemeName = prefs[COLOR_SCHEME] ?: ColorTheme.SYSTEM.name
                        colorTheme = ColorTheme.valueOf(colorSchemeName)
                        useDynamicColors = prefs[DYNAMIC_COLORS] ?: false
                    }
                }

                AppTheme(
                    colorTheme = colorTheme,
                    useDynamicColors = useDynamicColors
                ) {
                    Compose(savedInstanceState)
                }
            }
        }
    }

    @Composable
    abstract fun Compose(savedInstanceState: Bundle?)
}