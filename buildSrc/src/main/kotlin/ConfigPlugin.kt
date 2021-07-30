import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.api.JavaVersion
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.named


typealias App = com.android.build.gradle.AppPlugin
typealias Library = com.android.build.gradle.LibraryPlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

class ConfigPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(com.github.benmanes.gradle.versions.VersionsPlugin::class)
        target.tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
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

        target.subprojects {
            plugins.whenPluginAdded {
                if (this is App || this is Library) {
                    extensions.findByType<AndroidExtension>()?.applyCommonConfigs()
                }
            }
        }
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

        testInstrumentationRunner = "androidx.com.hotmart.test.test.runner.AndroidJUnitRunner"
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
            isTestCoverageEnabled = true
        }

        create("production") {
            isMinifyEnabled = true
            initWith(getByName("release"))
        }

        create("internal") {
            initWith(getByName("production"))
            isDebuggable = true
            isMinifyEnabled = false
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
        unitTests.all {
            it.testLogging {
                events = setOf(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_OUT,
                    TestLogEvent.STANDARD_ERROR
                )
            }
        }
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
        testBuildType = "local"
    }


    variantFilter {
        if (buildType.name.contains("release") || buildType.name.contains("debug")) {
            ignore = true
        }
    }
}