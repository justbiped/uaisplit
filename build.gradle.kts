import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

apply<VersionsPlugin>()
apply<ConfigPlugin>()

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Path.androidGradle)
        classpath(Path.kotlinGradle)
        classpath(Path.serialization)
        classpath(Path.dependenciesUpdate)
        classpath(Dependencies.Navigator.classPath)
        classpath(Dependencies.Hilt.classPath)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    afterEvaluate {
        if (instrumentation.hasManagedDevice) {
            androidTestTasks.add("$name:pixel2LocalAndroidTest")
        }
    }
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
