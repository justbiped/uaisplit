plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc02"
    }
}

dependencies {
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.icons)
    implementation(Dependencies.Android.Compose.iconsExtended)
    implementation(Dependencies.Android.Compose.animation)

    implementation(Dependencies.Android.Compose.foundation)
    implementation(Dependencies.Android.Compose.runtime)

    implementation(Dependencies.Android.Compose.toolingPreview)
    devImplementation(Dependencies.Android.Compose.tooling)

    implementation("io.coil-kt:coil-compose:2.1.0")
}