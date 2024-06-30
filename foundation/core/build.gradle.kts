plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.hilt)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.ksp)
    apply(libs.plugins.kotlin.parcelize)
    apply(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig{
        namespace = "com.favoriteplaces.core"
    }

    buildFeatures.apply {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":coroutines:core"))

    implementation(libs.coroutine.android)

    implementation(libs.android.core)
    implementation(libs.android.compat)

    implementation(libs.navigation.ui)

    implementation(libs.http)
    implementation(libs.http.retrofit)
    implementation(libs.http.logging)

    implementation(libs.kotlin.serialization.converter)
    implementation(libs.kotlin.serialization)

    implementation(libs.compose.navigation)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}
