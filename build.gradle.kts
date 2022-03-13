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
    val commands = StringBuilder("#!/bin/bash\n")
    val file = File("${rootProject.rootDir}/android-test.sh")
    file.createNewFile()

    subprojects {
        val projectName = name
        tasks.whenTaskAdded {
            if (name == "pixel2LocalAndroidTest" && instrumentation.hasManagedDevice) {
                commands.append("./gradlew $projectName:$name\n")
            }
        }
    }

    doLast { file.writeText(commands.toString()) }
}
