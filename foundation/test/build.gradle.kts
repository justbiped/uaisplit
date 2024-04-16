plugins {
    apply(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    apply(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.biped.test"
}

dependencies {
    implementation(libs.android.core)
    implementation(libs.android.compat)

    implementation(libs.test.truth)
    implementation(libs.test.mockk)

    implementation(libs.http.ok)
    implementation(libs.http.mockserver)

    implementation(libs.coroutine.test)
    implementation(libs.android.test.arch)
    implementation(libs.hilt.testing)
    implementation(libs.android.test.runner)
    implementation(libs.android.test.espresso)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
}