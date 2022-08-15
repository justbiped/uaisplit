plugins {
    id(Plugins.Android.library)
    id(Dependencies.Hilt.plugin)
    id(Dependencies.Kotlin.android)
    id(Dependencies.Kotlin.kapt)
    id(Dependencies.Kotlin.parcelize)
    id(Dependencies.Serialization.plugin)
}

dependencies {
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Fragment.core)
    implementation(Dependencies.Navigator.ui)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}
