plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.kotlin)
    id(Plugins.kapt)
    id(Plugins.parcelize)
    id(Plugins.serialization)
}

android {
    buildFeatures.apply {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compiler_vesion
    }
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

    implementation(Dependencies.Compose.navigation)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}
