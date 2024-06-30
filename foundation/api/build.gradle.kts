plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.hilt)
    apply(libs.plugins.ksp)
}

android {
    namespace = "biped.works.api"
}

dependencies {
    implementation(libs.http)
    implementation(libs.http.retrofit)
    implementation(libs.http.logging)

    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.converter)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}