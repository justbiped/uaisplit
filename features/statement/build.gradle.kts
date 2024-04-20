plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.compose)
}

android {
    namespace = "biped.works.statement"
}

dependencies {
    implementation(libs.android.core)

    implementation(project(":foundation:theme"))
    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:api"))
}