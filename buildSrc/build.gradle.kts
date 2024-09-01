plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins {
        register("jetpack-compose") {
            id = "biped.works.plugins.compose"
            implementationClass = "biped.works.plugins.ComposePlugin"
            version = "1.0.0"
        }
        register("test") {
            id = "biped.works.plugins.test"
            implementationClass = "biped.works.plugins.TestPlugin"
            version = "1.0.0"
        }
        register("github") {
            id = "biped.works.plugins.github.GitHubPlugin"
            implementationClass = "biped.works.github.GitHubPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.java.poet)
    implementation(libs.kotlin.kover)

    // https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}