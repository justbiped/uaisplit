package biped.works.plugins

import android
import androidTestImplementation
import id
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import kotlinx.kover.gradle.plugin.dsl.KoverReportsConfig
import libs
import local
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import testImplementation

const val UNIT_TEST_VARIANT = "unitTest"

class TestPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            project.plugins.apply(libs.plugins.kotlin.kover.id)

            android {
                kover {
                    currentProject {
                        createVariant(UNIT_TEST_VARIANT) {
                            add("local")
                        }
                    }
                    reports(reportsConfig)
                }

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

                dependencies {
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
}

internal fun Project.kover(action: KoverProjectExtension.() -> Unit) {
    extensions.findByType<KoverProjectExtension>()?.also(action)
}

val reportsConfig: KoverReportsConfig.() -> Unit = {
    filters.excludes.classes(
        "*_Hilt*",
        "*hilt_*",
        "*dagger*",
        "*.injection*",
        "*_Factory",
        "com.bumptech.glide*",
        "com.mlykotom.valifi*"
    )
    filters.excludes.androidGeneratedClasses()
}
