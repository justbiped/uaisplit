package biped.works.plugins

import AndroidExtension
import android
import library
import catalog
import requiredVersion
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
                    kotlinCompilerExtensionVersion = catalog.requiredVersion("compose.compiler")
                }
            }

            dependencies {
                implementation(catalog.library("compose.bom"))
                implementation(catalog.library("compose.foundation"))
                implementation(catalog.library("compose.ui"))
                implementation(catalog.library("compose.material"))
                implementation(catalog.library("compose.windowSizeClass"))
                implementation(catalog.library("compose.icons"))
                implementation(catalog.library("compose.iconsExtended"))
                implementation(catalog.library("compose.animation"))
                implementation(catalog.library("compose.hilt"))
                implementation(catalog.library("compose.coil"))

                // Preview
                implementation(catalog.library("compose.ui.tooling.preview"))
                devImplementation(catalog.library("compose.ui.tooling"))

                // Test
                testImplementation(catalog.library("compose.test.junit"))
                devImplementation(catalog.library("compose.test.manifest"))

                // Biped Compose Foundation
                implementation(project(":foundation:compose"))
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}