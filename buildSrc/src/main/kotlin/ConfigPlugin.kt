import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

typealias App = com.android.build.gradle.AppPlugin
typealias Library = com.android.build.gradle.LibraryPlugin
typealias AndroidExtension = com.android.build.gradle.TestedExtension

class ConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyAndroidConfigs()
    }
}

private fun Project.applyAndroidConfigs() {
    subprojects {
        onAndroidSetup {
            plugins.apply(catalog.plugin("kotlin.android").id)

            android {
                compileSdkVersion(34)
                buildToolsVersion("33.0.0")

                defaultConfig {
                    minSdk = 24
                    targetSdk = 34

                    testInstrumentationRunner =
                        "com.biped.test.instrumentation.runner.LocationTestRunner"
                }

                buildFeatures.apply {
                    viewBinding = true
                    buildConfig = true
                }

                compileOptions {
                    targetCompatibility = JavaVersion.VERSION_11
                    sourceCompatibility = JavaVersion.VERSION_11
                }

                tasks.withType<KotlinCompile> {
                    kotlinOptions { jvmTarget = "11" }
                }

                buildTypes {
                    create(local) {
                        initWith(getByName("debug"))
                    }

                    create(production) {
                        isMinifyEnabled = true
                        initWith(getByName("release"))
                    }

                    create(internal) {
                        initWith(getByName(production))
                        isMinifyEnabled = false
                    }
                }

                packagingOptions {
                    resources.excludes.add("META-INF/LICENSE*.md")
                    resources.pickFirsts.add("**/attach_hotspot_windows.dll")
                }

                setupTests()
            }

            androidComponents {
                beforeVariants {
                    if (it.name.contains("release") || it.name.contains("debug")) {
                        it.enable = false
                    }
                }
            }

            dependencies {
                localImplementation(catalog.library("android.test.core"))
                localImplementation(catalog.library("navigation.testing"))
                localImplementation(catalog.library("http.mockserver"))

                testImplementation(catalog.library("test.mockk"))
                testImplementation(catalog.library("coroutine.test"))
                testImplementation(catalog.library("android.test.core"))
                testImplementation(catalog.library("android.test.junit"))
                testImplementation(catalog.library("android.test.arch"))
                testImplementation(catalog.library("navigation.testing"))
                testImplementation(catalog.library("hilt.testing"))
                testImplementation(catalog.library("android.test.runner"))
                testImplementation(catalog.library("android.test.espresso"))
                testImplementation(catalog.library("android.test.contrib"))
                testImplementation(catalog.library("test.robolectric"))
                testImplementation(catalog.library("test.truth"))

                androidTestImplementation(catalog.library("android.test.junit"))
                androidTestImplementation(catalog.library("android.test.runner"))
                androidTestImplementation(catalog.library("android.test.espresso"))
                androidTestImplementation(catalog.library("android.test.contrib"))
                androidTestImplementation(catalog.library("navigation.testing"))
                androidTestImplementation(catalog.library("hilt.testing"))
                androidTestImplementation(catalog.library("test.robolectric.annotations"))
                androidTestImplementation(catalog.library("test.truth"))
            }
        }

    }
}

private fun AndroidExtension.setupTests() {
    testOptions {
        unitTests.all {
            it.testLogging {
                events = setOf(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_ERROR,
                    TestLogEvent.STANDARD_OUT
                )
            }
        }

        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
        testBuildType = local
    }
}

private fun Project.onAndroidSetup(action: () -> Unit) {
    plugins.whenPluginAdded {
        if (this is App || this is Library) action()
    }
}

internal fun Project.android(action: AndroidExtension.() -> Unit) {
    extensions.findByType<AndroidExtension>()?.action()
}

internal fun Project.androidComponents(action: ApplicationAndroidComponentsExtension.() -> Unit) {
    extensions.findByType<ApplicationAndroidComponentsExtension>()?.action()
}
