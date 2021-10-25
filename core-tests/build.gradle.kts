plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    localImplementation(Dependencies.Test.coroutines)
    localImplementation(Dependencies.Test.archCore)
    localImplementation(project(":core"))

    localImplementation(Dependencies.okHttp)

    localImplementation(Dependencies.Test.runner)
    localImplementation(Dependencies.Test.espresso)
    kaptLocal(Dependencies.daggerCompiler)

    localApi(Dependencies.Test.androidxCore)
    localApi(Dependencies.Test.fragment)
    localApi(Dependencies.Test.navigation)
    localApi(Dependencies.Test.mockServer)
}