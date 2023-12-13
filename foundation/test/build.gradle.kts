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
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Fragment.core)

    implementation(Dependencies.Test.truth)
    implementation(Dependencies.Test.mockk)

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Square.mockServer)

    implementation(Dependencies.Coroutines.test)
    implementation(Dependencies.Test.archCore)
    implementation(Dependencies.Fragment.testing)
    implementation(Dependencies.Hilt.testing)
    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)

    implementation(Dependencies.Hilt.core)
    ksp(Dependencies.Hilt.compiler)
}