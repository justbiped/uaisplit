plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.compose)
    id(Plugins.serialization)
}

android {
    namespace = "biped.works.transaction"
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")


    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
}