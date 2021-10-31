import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.*


typealias App = com.android.build.gradle.AppPlugin
typealias Library = com.android.build.gradle.LibraryPlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

class ConfigPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        configureDependencyUpdates(project)
        applyAndroidConfigs(project)
    }
}

private fun configureDependencyUpdates(project: Project) {
    project.plugins.apply(VersionsPlugin::class)
    project.tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
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
}

private fun applyAndroidConfigs(project: Project) {
    project.androidConfigs {
        compileSdkVersion(30)
        buildToolsVersion("30.0.3")

        defaultConfig {
            minSdkVersion(23)
            targetSdkVersion(30)

            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "com.hotmart.coretests.LocationTestRunner"
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

        packagingOptions {
            resources.excludes.add("META-INF/**")
            pickFirst("**/attach_hotspot_windows.dll")
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
}

fun Project.androidConfigs(block: AndroidExtension.() -> Unit) {
    subprojects {
        plugins.whenPluginAdded {
            if (this is App || this is Library) {
                extensions.findByType<AndroidExtension>()?.block()

                dependencies {
                    localImplementation(Dependencies.Test.androidxCore)
                    localImplementation(Dependencies.Test.fragment)
                    localImplementation(Dependencies.Test.navigation)
                    localImplementation(Dependencies.Test.mockServer)

                    testImplementation(Dependencies.Test.mockk)
                    testImplementation(Dependencies.Test.coroutines)
                    testImplementation(Dependencies.Test.androidxCore)
                    testImplementation(Dependencies.Test.androidxJunit)
                    testImplementation(Dependencies.Test.archCore)
                    testImplementation(Dependencies.Test.navigation)
                    testImplementation(Dependencies.Test.runner)
                    testImplementation(Dependencies.Test.espresso)
                    testImplementation(Dependencies.Test.espressoContrib)
                    testImplementation(Dependencies.Test.robolectric)
                    testImplementation(Dependencies.Test.assertJ)

                    androidTestImplementation(Dependencies.Test.mockkAndroid)
                    androidTestImplementation(Dependencies.Test.androidxJunit)
                    androidTestImplementation(Dependencies.Test.navigation)
                    androidTestImplementation(Dependencies.Test.runner)
                    androidTestImplementation(Dependencies.Test.espresso)
                    androidTestImplementation(Dependencies.Test.espressoContrib)
                    androidTestImplementation(Dependencies.Test.robolectricAnnotations)
                    androidTestImplementation(Dependencies.Test.assertJ)
                }
            }
        }
    }
}

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)