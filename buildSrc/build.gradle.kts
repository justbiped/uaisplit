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
            version = "1.0.0"
        }
        register("github"){
            id = "biped.works.plugins.github.GitHubPlugin"
            implementationClass = "biped.works.github.GitHubPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle)
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    implementation("com.squareup:javapoet:1.13.0")
}