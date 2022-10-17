plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.kapt)
    id(Plugins.compose)
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.datastore)

    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Navigator.ui)
    implementation(Dependencies.Navigator.fragment)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha02")


    implementation(project(":core"))
    implementation(project(":theme"))
    devImplementation(project(":test"))
    devImplementation(project(":settings"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}

