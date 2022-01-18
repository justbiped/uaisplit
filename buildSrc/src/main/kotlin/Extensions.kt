import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * ### Imports only on development
 * Use this to apply dependencies that should exists only on development environment.
 * Develop environment are:
 * - internal
 * - staging
 * - local
 **/
fun DependencyHandler.devImplementation(dependencyNotation: Any): Dependency? {
    add("internalImplementation", dependencyNotation)
    return add("localImplementation", dependencyNotation)
}

/**
 * ### Imports only on production
 * Use this to apply dependencies that only is used on production environment.
 **/
fun DependencyHandler.prodImplementation(dependencyNotation: Any) =
    add("productionImplementation", dependencyNotation)

/**
 * ### Imports only on local
 * The Local is used to run tests, so, the local implementation is design to
 * import dependencies for shared(Runs on JVM and ART) tests on android.
 * The tests under project/sharedTest will use this shared imports
 **/
fun DependencyHandler.localImplementation(dependencyNotation: Any) =
    add("localImplementation", dependencyNotation)

/**
 * ### Junit test imports
 * Used for import dependencies that is used for junit tests.
 * The tests under project/test will use this imports
 **/
fun DependencyHandler.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

/**
 * ### Instrumentation test imports
 * Used for import dependencies that is used for instrumentation tests.
 * The tests under project/androidTest will use this imports
 **/
fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)