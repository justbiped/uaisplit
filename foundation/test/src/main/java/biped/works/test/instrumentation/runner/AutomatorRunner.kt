package biped.works.test.instrumentation.runner

import java.lang.reflect.Method
import org.junit.Test
import org.junit.runner.Description
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod

class AutomatorRunner(private val testClass: Class<*>) : BlockJUnit4ClassRunner(testClass) {

    override fun getChildren(): MutableList<FrameworkMethod> {
        return sortTestClassMethods().map { FrameworkMethod(it) }.toMutableList()
    }

    override fun describeChild(frameworkMethod: FrameworkMethod): Description {
        val displayName = getDisplayName(frameworkMethod.method)

        return Description.createTestDescription(
            testClass.name,
            displayName,
            frameworkMethod.method.annotations
        )
    }

    private fun sortTestClassMethods(): List<Method> {
        return testClass.methods
            .filter { it.getAnnotation(Test::class.java) != null }
            .sortedBy { it.getAnnotation(Step::class.java)?.order ?: 0 }
    }

    private fun getDisplayName(method: Method): String {
        fun formatMethodName(name: String): String {
            return name.replace("_", " ")
        }

        val annotation = method.getAnnotation(Step::class.java)
        return annotation?.displayName ?: formatMethodName(method.name)
    }

}


