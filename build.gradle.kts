import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()
apply<ConfigPlugin>()

plugins {
    id(Plugins.application).apply(false)
    id(Plugins.library).apply(false)
    id(Plugins.play_services).version(Plugins.play_services_version).apply(false)
    id(Plugins.kotlin).apply(false)
    id("com.google.devtools.ksp").version("1.9.10-1.0.13").apply(false)
    id(Plugins.serialization).version(Plugins.serialization_version).apply(false)
    id(Plugins.safe_args).version(Plugins.safe_args_version).apply(false)
    id(Plugins.hilt).version(Plugins.hilt_version).apply(false)
    id(Plugins.dependency_updates).version(Plugins.dependency_updates_version).apply(false)
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "report/dependencies"
    reportfileName = "available-versions"

    rejectVersionIf {
        candidate.version.contains("alpha") ||
                candidate.version.contains("beta") ||
                candidate.version.contains("SNAPSHOT")
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
