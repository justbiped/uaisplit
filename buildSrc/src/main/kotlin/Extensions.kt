import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.localImplementation(dependencyNotation: Any): Dependency? =
    add("localImplementation", dependencyNotation)