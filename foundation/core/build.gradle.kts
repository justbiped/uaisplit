plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.kotlin)
    id(Plugins.ksp)
    id(Plugins.parcelize)
    id(Plugins.serialization)
}

android {
    buildFeatures.apply {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compiler_version
    }
}

dependencies {
    implementation(project(":coroutines:core"))
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Fragment.core)
    implementation(Dependencies.Navigation.ui)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.Square.httpLogging)
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Navigation.compose)

    implementation(Dependencies.Hilt.core)
    ksp(Dependencies.Hilt.compiler)
}
