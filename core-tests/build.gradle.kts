plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
    implementation(project(":core"))

    implementation(Dependencies.okHttp)
    implementation(Dependencies.Test.mockServer)

    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)
    kapt(Dependencies.daggerCompiler)
}