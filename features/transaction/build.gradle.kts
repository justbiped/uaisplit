plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.compose)
    id(Plugins.serialization)
    id(Plugins.kapt)
}

android {
    namespace = "biped.works.transaction"
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Square.retrofit)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(Dependencies.Serialization.core)

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
}