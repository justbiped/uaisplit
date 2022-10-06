import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()
apply<ConfigPlugin>()

plugins {
    id(Plugins.Application).apply(false)
    id(Plugins.Library).apply(false)
    id(Plugins.PlayServices).version(Plugins.PlayServicesVersion).apply(false)
    id(Plugins.Kotlin).apply(false)
    id(Plugins.Serialization).version(Plugins.SerializationVersion).apply(false)
    id(Plugins.SafeArgs).version(Plugins.SafeArgsVersion).apply(false)
    id(Plugins.Hilt).version(Plugins.HiltVersion).apply(false)
    id(Plugins.DependenciesUpdate).version(Plugins.DependenciesUpdateVersion).apply(false)
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

tasks.register("prepareAndroidTest") {
    val file = File("${rootProject.rootDir}/android-test.sh").apply {
        delete()
        createNewFile()
    }

    val commandBuilder = StringBuilder("#!/bin/bash").apply {
        androidTestTasks.forEach { append("\n./gradlew $it") }
    }

    file.writeText(commandBuilder.toString())
}
