plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.compose)
}

dependencies {
    implementation(Dependencies.Android.datastore)

    implementation(project(":core"))
    implementation(project(":theme"))

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}