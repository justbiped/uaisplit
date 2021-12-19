plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.serialization)
}

dependencies {
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appCompat)
    implementation(Dependencies.Androidx.materialDesign)
    implementation(Dependencies.Androidx.fragment)
    implementation(Dependencies.Androidx.navigationUI)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serializationConverter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.serialization)

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)
}
