import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.codehaus.groovy.tools.groovydoc.Main.execute

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
        classpath(Path.safeArgs)
        classpath(Path.hilt)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
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

tasks.create("androidTest") {
    val androidTestTask = this
    var previousTask: Task? = null

    subprojects {
        tasks.whenTaskAdded {
            if (name == "pixel2LocalAndroidTest") {
                if (previousTask == null) {
                    androidTestTask.dependsOn(this)
                } else {
                    previousTask?.shouldRunAfter(this)
                }
                previousTask = this
            }
        }
    }
}