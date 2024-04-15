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
    implementation(libs.serialization.converter)
    implementation(libs.http.logging)
    implementation(libs.http.ok)
    implementation(libs.serialization.core)

    implementation(Dependencies.Navigation.compose)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
