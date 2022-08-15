plugins {
    id(Plugins.Android.library)
    id(Dependencies.Hilt.plugin)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.serialization)
    id(Plugins.Kotlin.parcelize)
}

dependencies {
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.Fragment.fragment)
    implementation(Dependencies.Navigator.ui)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serializationConverter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.serialization)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}
