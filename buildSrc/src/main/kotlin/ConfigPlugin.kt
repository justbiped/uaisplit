import com.android.build.api.dsl.Device
import com.android.build.api.dsl.ManagedVirtualDevice
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.*
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
            plugins.apply(Plugins.Kotlin.android)
            plugins.apply(Plugins.Android.safeArgs)

            android {
                compileSdkVersion(32)
                buildToolsVersion("33.0.0")

                defaultConfig {
                    minSdk = 24
                    targetSdk = 30

                    testInstrumentationRunner =
                        "com.favoriteplaces.core.test.instrumentation.runner.LocationTestRunner"
                }

                buildFeatures.apply {
                    viewBinding = true
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
                    resources.excludes.add("META-INF/**")
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
                testImplementation(Dependencies.Test.hilt)
                testImplementation(Dependencies.Test.runner)
                testImplementation(Dependencies.Test.espresso)
                testImplementation(Dependencies.Test.espressoContrib)
                testImplementation(Dependencies.Test.robolectric)
                testImplementation(Dependencies.Test.assertJ)

                androidTestImplementation(Dependencies.Test.mockkAndroid)
                androidTestImplementation(Dependencies.Test.androidxJunit)
                androidTestImplementation(Dependencies.Test.navigation)
                androidTestImplementation(Dependencies.Test.hilt)
                androidTestImplementation(Dependencies.Test.runner)
                androidTestImplementation(Dependencies.Test.espresso)
                androidTestImplementation(Dependencies.Test.espressoContrib)
                androidTestImplementation(Dependencies.Test.robolectricAnnotations)
                androidTestImplementation(Dependencies.Test.assertJ)
            }
        }

    }
}

private fun AndroidExtension.setupTests() {

    testOptions {

        devices {
            createPixel2()
        }

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

private fun NamedDomainObjectContainerScope<Device>.createPixel2() {
    if (findByName("pixel2") == null) {
        create<ManagedVirtualDevice>("pixel2") {
            device = "Pixel 2"
            apiLevel = 29
            systemImageSource = "aosp"
            abi = "x86"
        }
    } else {
        getByName<ManagedVirtualDevice>("pixel2")
    }
}

private fun Project.onAndroidSetup(action: () -> Unit) {
    plugins.whenPluginAdded {
        if (this is App || this is Library) action()
    }
}

private fun Project.android(action: AndroidExtension.() -> Unit) {
    extensions.findByType<AndroidExtension>()?.action()
}

private fun Project.androidComponents(action: ApplicationAndroidComponentsExtension.() -> Unit) {
    extensions.findByType<ApplicationAndroidComponentsExtension>()?.action()
}
