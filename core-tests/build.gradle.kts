plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

dependencies {
    devImplementation(Dependencies.Test.coroutines)
    devImplementation(Dependencies.Test.archCore)
    devImplementation(project(":core"))

    devImplementation(Dependencies.okHttp)

    devImplementation(Dependencies.Test.runner)
    devImplementation(Dependencies.Test.espresso)
    kaptLocal(Dependencies.daggerCompiler)
}