plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.ksp)
    id(Plugins.compose)
}

android{
    namespace = "biped.works.user"
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.datastore)

    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Navigation.ui)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation("com.squareup:kotlinpoet:1.16.0")


    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    devImplementation(project(":foundation:test"))

    implementation(project(":database"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
}

