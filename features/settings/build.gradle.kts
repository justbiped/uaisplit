plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.ksp)
    id(Plugins.compose)
}

android{
    namespace = "biped.works.settings"
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.datastore)

    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Navigation.ui)
    implementation(Dependencies.Navigation.fragment)

    implementation(Dependencies.Hilt.core)
    ksp(Dependencies.Hilt.compiler)

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    devImplementation(project(":foundation:test"))

    implementation(project(":features:user"))
    implementation(project(":features:profile"))

    implementation(project(":database"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kspTest(Dependencies.Hilt.compiler)
    kspAndroidTest(Dependencies.Hilt.compiler)
}