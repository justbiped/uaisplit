plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.39.0")
}