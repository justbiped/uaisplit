package com.biped.locations.user.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.favoriteplaces.core.compose.Launch

private data class ComposeState(val isLoading: Boolean = false) {
    fun default() = copy(isLoading = false)
    fun loading() = copy(isLoading = true)
}

@Composable
internal fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    var state by remember { mutableStateOf(ComposeState()) }

    Launch {
        viewModel.instruction.collect { instruction ->
            state = when (instruction) {
                is Instruction.Default -> state.default()
                is Instruction.Loading -> state.loading()
            }
        }
    }
    
    ProfileUi(state = state)
}

@Composable
private fun ProfileUi(state: ComposeState) {
}
