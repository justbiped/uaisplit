package biped.works.plugins

import AndroidExtension
import android
import devImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
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

fun VersionCatalog.library(key: String) = findLibrary(key).get()
fun VersionCatalog.requiredVersion(key:String) : String = findVersion(key).get().requiredVersion