plugins {
    id(Plugins.Android.library)
    id(Dependencies.Kotlin.android)
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Android.materialDesign)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.animation)
    implementation("androidx.compose.material3:material3:1.0.0-beta01")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-beta01")

    implementation(Dependencies.Compose.foundation)

    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)

    implementation(Dependencies.coil)
}