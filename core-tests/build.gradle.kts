plugins {
    id(Plugins.Android.library)
    id(Plugins.Android.hilt)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
    implementation(project(":core"))

    implementation(Dependencies.okHttp)
    implementation(Dependencies.Test.mockServer)

    implementation(Dependencies.Android.hilt)
    kapt(Dependencies.Android.hiltCompiler)
}