plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin{
    plugins {
        register("jetpack-compose") {
            id = "biped.works.plugins.compose"
            implementationClass = "biped.works.plugins.ComposePlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation("com.squareup:javapoet:1.13.0")
}