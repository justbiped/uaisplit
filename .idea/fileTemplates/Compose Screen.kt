#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME}#end

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import biped.works.compose.collectWithLifecycle

@Stable
private class ${NAME}State {
    var isLoading: Boolean by mutableStateOf(false)
        private set

    fun default() {
        isLoading = false
    }

    fun loading() {
        isLoading = true
    }
}

@Composable
private fun remember${NAME}State() = remember {
    mutableStateOf(${NAME}State())
}

@Composable
internal fun ${NAME}Screen(viewModel : ${NAME}ViewModel = viewModel()){
    val state by remember${NAME}State()

    viewModel.instruction.collectWithLifecycle { instruction ->
        when (instruction) {
            is Instruction.Default -> state.default()
            is Instruction.Loading -> state.loading()
        }
    }

    ${NAME}Ui(state = state)
}

@Composable
private fun ${NAME}Ui(state : ${NAME}State){
}