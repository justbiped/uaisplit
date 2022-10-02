plugins {
    id(Plugins.Library)
    id(Plugins.Hilt)
    id(Plugins.Android)
    id(Plugins.Kapt)
    id(Plugins.Parcelize)
    id(Plugins.Serialization)
}

dependencies {
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Fragment.core)
    implementation(Dependencies.Navigator.ui)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.Square.httpLogging)
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}
