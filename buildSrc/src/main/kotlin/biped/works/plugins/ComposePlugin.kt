package biped.works.plugins

import android
import devImplementation
import id
import libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import testImplementation

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            project.plugins.apply(libs.plugins.compose.compiler.id)

            android {
                buildFeatures.apply {
                    compose = true
                }
            }

            dependencies {
                implementation(platform(libs.compose.bom))
                implementation(libs.compose.foundation)
                implementation(libs.compose.ui)
                implementation(libs.compose.icons)
                implementation(libs.compose.iconsExtended)
                implementation(libs.compose.animation)
                implementation(libs.compose.hilt)
                implementation(libs.compose.coil)

                implementation(libs.compose.material)
                implementation(libs.compose.material.window)
                implementation(libs.compose.material.navigation)

                // Navigation
                implementation(libs.compose.navigation)

                // Preview
                implementation(libs.compose.ui.tooling.preview)
                devImplementation(libs.compose.ui.tooling)

                // Test
                testImplementation(libs.compose.test.junit)
                devImplementation(libs.compose.test.manifest)

                // Biped Compose Foundation
                implementation(project(":foundation:compose"))
            }
        }
    }
}

private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}