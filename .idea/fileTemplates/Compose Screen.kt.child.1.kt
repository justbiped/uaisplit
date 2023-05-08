#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME}#end

import androidx.lifecycle.ViewModel
import biped.works.coroutines.MutableWarmFlow

internal class ${NAME}ViewModel : ViewModel(){

    private val _instruction = MutableWarmFlow<${NAME}UiState>(${NAME}UiState.Update${NAME}()) 
    val instruction = _instruction.toWarmFlow()

}