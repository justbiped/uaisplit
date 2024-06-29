plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.kotlin.serialization)
    apply(libs.plugins.hilt)
    apply(libs.plugins.compose)
    apply(libs.plugins.ksp)
}

android {
    namespace = "biped.works.transaction"
}

dependencies {
    implementation(project(":foundation:api"))
    implementation(project(":foundation:theme"))
    implementation(project(":coroutines:core"))

    implementation(libs.android.core)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.http)
    implementation(libs.http.retrofit)

    implementation(libs.serialization.core)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}