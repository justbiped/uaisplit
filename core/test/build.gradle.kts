plugins {
    id(Plugins.Android.library)
    id(Plugins.Android.hilt)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.fragment)

    implementation(project(":core"))
    implementation(Dependencies.okHttp)
    implementation(Dependencies.Test.mockServer)

    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
    implementation(Dependencies.Test.fragment)
    implementation(Dependencies.Test.hilt)
    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)

    implementation(Dependencies.Android.hilt)
    kapt(Dependencies.Android.hiltCompiler)
}