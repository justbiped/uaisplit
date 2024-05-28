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
                implementation(libs.timber)

                localImplementation(libs.android.test.core)
                localImplementation(libs.navigation.testing)
                localImplementation(libs.http.mockserver)

                testImplementation(libs.test.mockk)
                testImplementation(libs.coroutine.test)
                testImplementation(libs.android.test.core)
                testImplementation(libs.android.test.junit)
                testImplementation(libs.android.test.arch)
                testImplementation(libs.navigation.testing)
                testImplementation(libs.hilt.testing)
                testImplementation(libs.android.test.runner)
                testImplementation(libs.android.test.espresso)
                testImplementation(libs.android.test.contrib)
                testImplementation(libs.test.robolectric)
                testImplementation(libs.test.truth)

                androidTestImplementation(libs.android.test.junit)
                androidTestImplementation(libs.android.test.runner)
                androidTestImplementation(libs.android.test.espresso)
                androidTestImplementation(libs.android.test.contrib)
                androidTestImplementation(libs.navigation.testing)
                androidTestImplementation(libs.hilt.testing)
                androidTestImplementation(libs.test.robolectric.annotations)
                androidTestImplementation(libs.test.truth)
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
