package biped.works.plugins

import AndroidExtension
import Dependencies
import android
import devImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import testImplementation

val noAndroidPluginException: Throwable
    get() = Throwable("Make sure that you have declared the Application or Library plugin")

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.allprojects {

            project.extensions.findByType<AndroidExtension>() ?: noAndroidPluginException

            android {
                buildFeatures.apply {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = Dependencies.Compose.compiler_vesion
                }
            }

            dependencies {
                implementation(enforcedPlatform(Dependencies.Compose.bom))
                implementation(Dependencies.Compose.foundation)
                implementation(Dependencies.Compose.ui)
                implementation(Dependencies.Compose.material)
                implementation(Dependencies.Compose.material_window)
                implementation(Dependencies.Compose.icons)
                implementation(Dependencies.Compose.iconsExtended)
                implementation(Dependencies.Compose.animation)
                implementation(Dependencies.Compose.hilt)
                implementation(Dependencies.Compose.coil)

                // Preview
                implementation(Dependencies.Compose.tooling_preview)
                devImplementation(Dependencies.Compose.tooling)

                // Test
                testImplementation(Dependencies.Compose.test_junit)
                devImplementation(Dependencies.Compose.test_manifest)

                // Biped Compose Foundation
                implementation(project(":foundation:compose"))
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}