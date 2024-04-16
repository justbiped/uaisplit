plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.kotlin)
    id(Plugins.ksp)
}

android {
    namespace = "com.biped.test"
}

dependencies {
    implementation(libs.android.core)
    implementation(libs.android.compat)

    implementation(libs.test.truth)
    implementation(libs.test.mockk.mockk)

    implementation(libs.http.ok)
    implementation(libs.http.mockServer)

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