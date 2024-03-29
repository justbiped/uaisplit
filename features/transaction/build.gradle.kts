plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.compose)
}

android {
    namespace = "biped.works.transaction"
}

dependencies {
    implementation(Dependencies.Android.core)

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
}