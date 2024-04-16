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

    implementation(libs.coroutine.android)

    implementation(libs.android.core)
    implementation(libs.android.compat)

    implementation(libs.navigation.ui)

    implementation(libs.http.retrofit)
    implementation(libs.serialization.converter)
    implementation(libs.http.logging)
    implementation(libs.http.ok)
    implementation(libs.serialization.core)

    implementation(libs.compose.navigation)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
