plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.hilt)
    apply(libs.plugins.ksp)
    apply(libs.plugins.biped.compose)
    apply(libs.plugins.biped.test)
}

android {
    namespace = "biped.works.profile"
}

dependencies {
    implementation(libs.android.core)
    implementation(libs.android.compat)
    implementation(libs.android.datastore)

    implementation(libs.lifecycle.runtime)

    implementation(libs.navigation.ui)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    devImplementation(project(":foundation:test"))

    implementation(project(":features:user:business"))

    implementation(project(":database"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
}