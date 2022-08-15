plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
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
    implementation(Dependencies.Android.materialDesign)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.animation)

    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.runtime)

    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)

    implementation(Dependencies.coil)
}