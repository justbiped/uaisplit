import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.devImplementation(dependencyNotation: Any): Dependency? {
    add("internalImplementation", dependencyNotation)
    return add("localImplementation", dependencyNotation)
}

fun DependencyHandler.localImplementation(dependencyNotation: Any): Dependency? {
    return add("localImplementation", dependencyNotation)
}

fun DependencyHandler.kaptDev(dependencyNotation: Any): Dependency? {
    add("kaptInternal", dependencyNotation)
    return add("kaptLocal", dependencyNotation)
}