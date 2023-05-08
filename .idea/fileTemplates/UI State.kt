#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME}#end

import biped.works.coroutines.MutableInstructionFlow

internal sealed interface ${NAME}UiState {
    data class Update${NAME}(val isLoading : Boolean = false) : ${NAME}UiState 
    object ShowSomeMessage : ${NAME}UiState
}

internal fun MutableInstructionFlow<${NAME}UiState>.update (
    action: ${NAME}UiState.Update${NAME}.() -> ${NAME}UiState.Update${NAME}
) {
    (value as? ${NAME}UiState.Update${NAME})?.also { value = action(it) }
}