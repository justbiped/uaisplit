import Build_gradle.*
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

typealias App = com.android.build.gradle.AppPlugin
typealias Library = com.android.build.gradle.LibraryPlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Path.androidGradle)
        classpath(Path.kotlinGradle)
        // classpath(Path.serialization)
        classpath(Path.dependenciesUpdate)
    }
}

allprojects {
    apply<com.github.benmanes.gradle.versions.VersionsPlugin>()

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

    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    plugins.whenPluginAdded {
        if (this is App || this is Library) {
            extensions.findByType<AndroidExtension>()?.applyCommonConfigs()
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += "-Xallow-result-return-type"
        kotlinOptions.jvmTarget = "1.8"
    }
}

fun AndroidExtension.applyCommonConfigs() {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.apply {
        viewBinding = true
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        create("local") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".local"
            isTestCoverageEnabled = true
        }

        create("production") {
            initWith(getByName("release"))
        }
    }

    sourceSets {
        val sharedTest = "src/sharedTest"
        getByName("test") {
            java.srcDir("$sharedTest/java")
            resources.srcDirs("$sharedTest/resources")
        }
        getByName("androidTest") {
            java.srcDir("$sharedTest/java")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        testBuildType = "local"
    }


    variantFilter {
        if (buildType.name.contains("release") || buildType.name.contains("debug")) {
            ignore = true
        }
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}