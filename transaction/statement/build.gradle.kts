plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.kotlin.serialization)
    apply(libs.plugins.hilt)
    apply(libs.plugins.compose)
    apply(libs.plugins.ksp)
}

android {
    namespace = "biped.works.statement"
}

dependencies {
    implementation(libs.android.core)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.http)
    implementation(libs.http.retrofit)

    implementation(libs.kotlin.serialization)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(project(":foundation:theme"))
    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:api"))
}