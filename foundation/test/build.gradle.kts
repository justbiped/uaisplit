plugins {
    id(Plugins.Android.library)
    id(Dependencies.Hilt.plugin)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.Fragment.fragment)

    implementation(project(":core"))
    implementation(project(":theme"))
    implementation(Dependencies.okHttp)
    implementation(Dependencies.Test.mockServer)

    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
    implementation(Dependencies.Android.Fragment.test)
    implementation(Dependencies.Hilt.testing)
    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}