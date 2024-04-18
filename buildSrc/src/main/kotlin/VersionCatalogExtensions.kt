import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

val Project.catalog: VersionCatalog get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.library(key: String): Provider<MinimalExternalModuleDependency> {
    val library = findLibrary(key)
    return if (library.isPresent) library.get() else throw Exception("Unable to find $key in the version catalog")
}

fun VersionCatalog.plugin(key: String): Provider<PluginDependency> {
    val plugin = findPlugin(key)
    return if (plugin.isPresent) plugin.get() else throw Exception("Unable to find $key in the version catalog")
}

val Provider<PluginDependency>.id: String get() = get().pluginId

fun VersionCatalog.requiredVersion(key: String): String = findVersion(key).get().requiredVersion