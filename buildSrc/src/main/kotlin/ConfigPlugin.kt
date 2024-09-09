import biped.works.plugins.NoAndroidPluginException
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
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
        setupAndroidProjects {
            plugins.apply(libs.plugins.kotlin.android.id)
            plugins.apply(libs.plugins.kotlin.kover.id)

            android {
                compileSdkVersion(35)
                buildToolsVersion("34.0.0")

                defaultConfig {
                    minSdk = 29
                    targetSdk = 35
                }

                buildFeatures.apply {
                    buildConfig = true
                }

                compileOptions {
                    targetCompatibility = JavaVersion.VERSION_17
                    sourceCompatibility = JavaVersion.VERSION_17
                }

                kotlin {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
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
                    resources.excludes.add("META-INF/LICENSE*.md")
                    resources.pickFirsts.add("**/attach_hotspot_windows.dll")
                }
            }

            kotlin {
                sourceSets.all {
                    languageSettings.enableLanguageFeature("ExplicitBackingFields")
                }
            }

            androidComponents {
                beforeVariants {
                    if (it.name.contains("release") || it.name.contains("debug")) {
                        it.enable = false
                    }
                }
            }

            dependencies {
                implementation(libs.timber)
            }
        }

    }
}

private fun Project.setupAndroidProjects(action: () -> Unit) {
    plugins.whenPluginAdded {
        if (this is App || this is Library) action()
    }
}

internal fun Project.android(action: AndroidExtension.() -> Unit) {
    extensions.findByType<AndroidExtension>()?.action() ?: throw NoAndroidPluginException()
}

internal fun Project.androidComponents(action: ApplicationAndroidComponentsExtension.() -> Unit) {
    extensions.findByType<ApplicationAndroidComponentsExtension>()?.action()
}

internal fun Project.kotlin(action: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.findByType<KotlinAndroidProjectExtension>()?.action()
}
