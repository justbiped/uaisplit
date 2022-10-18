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
                implementation(Dependencies.Compose.foundation)
                implementation(Dependencies.Compose.ui)
                implementation("androidx.compose.material3:material3:1.0.0-rc01")
                implementation("androidx.compose.material3:material3-window-size-class:1.0.0-rc01")
                implementation(Dependencies.Compose.icons)
                implementation(Dependencies.Compose.iconsExtended)
                implementation(Dependencies.Compose.animation)
                implementation(Dependencies.Compose.activity)
                implementation(Dependencies.Compose.navigation)
                implementation(Dependencies.Compose.hilt)
                implementation(Dependencies.Compose.coil)

                //Preview
                implementation(Dependencies.Compose.toolingPreview)
                devImplementation(Dependencies.Compose.tooling)
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}