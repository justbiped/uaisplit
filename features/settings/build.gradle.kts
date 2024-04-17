plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    apply(libs.plugins.ksp)
    id(Plugins.compose)
}

android{
    namespace = "biped.works.settings"
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

    implementation(project(":features:user"))
    implementation(project(":features:profile"))

    implementation(project(":database"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
}