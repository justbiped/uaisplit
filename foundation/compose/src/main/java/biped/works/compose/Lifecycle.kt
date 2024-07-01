package biped.works.compose

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@SuppressLint("ComposableNaming")
@Composable
inline fun <reified T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline block: suspend (T) -> Unit
) {
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(lifecycleState) {
            collect { block(it) }
        }
    }
}