import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

val Project.catalog: VersionCatalog get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.library(key: String): Provider<MinimalExternalModuleDependency> {
    val library = findLibrary(key)
    return if (library.isPresent) library.get() else throw Exception("Unable to find $key in the version catalog")
}

fun VersionCatalog.requiredVersion(key: String): String = findVersion(key).get().requiredVersion