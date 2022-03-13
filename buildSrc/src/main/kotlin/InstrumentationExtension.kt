import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

const val INSTRUMENTATION_SETUP_KEY = "instrumentationSetupKey"

data class Instrumentation(
    var isEnabled: Boolean = false,
    var hasManagedDevice: Boolean = false
)

fun Project.instrumentation(setup: Instrumentation.() -> Unit) {
    val instrumentation = Instrumentation()
    setup(instrumentation)

    extra.set(INSTRUMENTATION_SETUP_KEY, instrumentation)
}

fun Project.getInstrumentation(): Instrumentation {
    return if (extra.has(INSTRUMENTATION_SETUP_KEY)) {
        extra.get(INSTRUMENTATION_SETUP_KEY) as Instrumentation
    } else {
        Instrumentation()
    }
}