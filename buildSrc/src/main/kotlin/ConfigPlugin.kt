import com.android.build.api.dsl.Device
import com.android.build.api.dsl.ManagedVirtualDevice
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
        androidConfigs {
            compileSdkVersion(31)
            buildToolsVersion("30.0.3")

            defaultConfig {
                minSdk = 23
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

            buildVariant()
            packingOptions()
            testSetup()
        }
    }
}

private fun AndroidExtension.buildVariant() {
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

    variantFilter {
        if (buildType.name.contains("release") || buildType.name.contains("debug")) {
            ignore = true
        }
    }
}

private fun AndroidExtension.packingOptions() {
    packagingOptions {
        resources.excludes.add("META-INF/**")
        resources.pickFirsts.add("**/attach_hotspot_windows.dll")
    }
}

private fun AndroidExtension.testSetup() {
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

        devices {
            if (findByName("pixel2") == null) createPixel2() else setupPixel2()
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
        testBuildType = "local"
    }
}

private fun NamedDomainObjectContainerScope<Device>.createPixel2() {
    create<ManagedVirtualDevice>("pixel2") {
        device = "Pixel 2"
        apiLevel = 29
        systemImageSource = "aosp"
        abi = "x86"
    }
}

private fun NamedDomainObjectContainerScope<Device>.setupPixel2() {
    getByName<ManagedVirtualDevice>("pixel2") {}
}

fun Project.androidConfigs(block: AndroidExtension.() -> Unit) {
    plugins.whenPluginAdded {
        if (this is App || this is Library) {
            plugins.apply(Plugins.Kotlin.android)
            plugins.apply(Plugins.Android.safeArgs)

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
