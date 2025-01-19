plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.hilt)
    apply(libs.plugins.ksp)
    apply(libs.plugins.biped.test)
}

android {
    namespace = "biped.works.user"
}

dependencies {
    implementation(project(":foundation:core"))
    implementation(project(":coroutines:core"))
    implementation(project(":database"))

    devImplementation(project(":foundation:test"))
    testImplementation(project(":coroutines:test"))

    implementation(libs.android.core)
    implementation(libs.android.compat)
    implementation(libs.android.datastore)

    implementation(libs.lifecycle.runtime)

    implementation(libs.navigation.ui)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    kspTest(libs.hilt.compiler)
}