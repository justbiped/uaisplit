package biped.works.plugins

import AndroidExtension
import android
import biped.works.library
import biped.works.libs
import biped.works.requiredVersion
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
                    kotlinCompilerExtensionVersion = libs.requiredVersion("compose.compiler")
                }
            }

            dependencies {
                implementation(libs.library("compose.bom"))
                implementation(libs.library("compose.foundation"))
                implementation(libs.library("compose.ui"))
                implementation(libs.library("compose.material"))
                implementation(libs.library("compose.windowSizeClass"))
                implementation(libs.library("compose.icons"))
                implementation(libs.library("compose.iconsExtended"))
                implementation(libs.library("compose.animation"))
                implementation(libs.library("compose.hilt"))
                implementation(libs.library("compose.coil"))

                // Preview
                implementation(libs.library("compose.toolingPreview"))
                devImplementation(libs.library("compose.tooling"))

                // Test
                testImplementation(libs.library("compose.testJunit"))
                devImplementation(libs.library("compose.testManifest"))

                // Biped Compose Foundation
                implementation(project(":foundation:compose"))
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}