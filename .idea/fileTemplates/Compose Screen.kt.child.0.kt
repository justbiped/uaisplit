#if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME}#end

internal sealed interface Instruction {
    object Default : Instruction 
    object Loading : Instruction
}