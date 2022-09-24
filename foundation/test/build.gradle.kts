plugins {
    id(Plugins.Android.library)
    id(Dependencies.Hilt.plugin)
    id(Dependencies.Kotlin.android)
    id(Dependencies.Kotlin.kapt)
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Fragment.core)

    implementation(Dependencies.Test.assertJ)
    implementation(Dependencies.Test.mockk)

    implementation(project(":core"))
    implementation(project(":theme"))
    implementation(Dependencies.okHttp)
    implementation(Dependencies.Test.mockServer)

    implementation(Dependencies.Coroutines.test)
    implementation(Dependencies.Test.archCore)
    implementation(Dependencies.Fragment.testing)
    implementation(Dependencies.Hilt.testing)
    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}