plugins {
    id(Plugins.library)
    apply(libs.plugins.kotlin.android)
    id(Plugins.compose)
}

android {
    namespace = "biped.works.transaction"
}

dependencies {
    implementation(libs.android.core)

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
}