package biped.works.compose

import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.core.view.WindowCompat

val LocalWindow = compositionLocalOf<Window?> { null }

@Composable
fun LocalWindowProvider(window: Window, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalWindow provides window,
        content
    )
}

fun Window.setStatusBarContrast(color: Color) {
    val isLight = if (color.luminance() > 0.5) true else false
    WindowCompat.getInsetsController(this, decorView).isAppearanceLightStatusBars = isLight
}