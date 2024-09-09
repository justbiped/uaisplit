package biped.works.compose.animtation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }

@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.block(
    modifier: Modifier.() -> Modifier
): Modifier = modifier(Modifier)

@Composable
fun AnimatedContentScope.LocalAnimatedVisibilityProvider(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalNavAnimatedVisibilityScope provides this,
        content = content
    )
}