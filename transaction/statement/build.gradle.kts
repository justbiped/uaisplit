plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.kotlin.serialization)
    apply(libs.plugins.hilt)
    apply(libs.plugins.ksp)
    apply(libs.plugins.biped.compose)
    apply(libs.plugins.biped.test)
}

android {
    namespace = "biped.works.statement"
}

dependencies {
    implementation(project(":transaction:transaction"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:api"))
    implementation(project(":foundation:theme"))

    implementation(project(":coroutines:core"))
    implementation(libs.runtime.android)
    testImplementation(project(":coroutines:test"))

    implementation(libs.android.core)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine.android)

    implementation(libs.http)
    implementation(libs.http.retrofit)

    implementation(libs.kotlin.serialization)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}