package biped.works.plugins

import AndroidExtension
import Dependencies
import android
import devImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import testImplementation

val noAndroidPluginException: Throwable
    get() = Throwable("Make sure that you have declared the Application or Library plugin")

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.allprojects {
            project.extensions.findByType<AndroidExtension>() ?: noAndroidPluginException
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            android {
                buildFeatures.apply {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = Dependencies.Compose.compiler_version
                }
            }

            dependencies {
                implementation(libs.findLibrary("compose.bom").get())
                implementation(libs.findLibrary("compose.foundation").get())
                implementation(libs.findLibrary("compose.ui").get())
                implementation(libs.findLibrary("compose.material").get())
                implementation(libs.findLibrary("compose.windowSizeClass").get())
                implementation(libs.findLibrary("compose.icons").get())
                implementation(libs.findLibrary("compose.iconsExtended").get())
                implementation(libs.findLibrary("compose.animation").get())
                implementation(libs.findLibrary("compose.hilt").get())
                implementation(libs.findLibrary("compose.coil").get())

                // Preview
                implementation(libs.findLibrary("compose.toolingPreview").get())
                devImplementation(libs.findLibrary("compose.tooling").get())

                // Test
                testImplementation(libs.findLibrary("compose.testJunit").get())
                devImplementation(libs.findLibrary("compose.testManifest").get())

                // Biped Compose Foundation
                implementation(project(":foundation:compose"))
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}