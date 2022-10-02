plugins {
    id(Plugins.Library)
    id(Plugins.Android)
    id(Plugins.Kapt)
    id(Plugins.Hilt)
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
    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)
    implementation("androidx.compose.material3:material3:1.0.0-beta02")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-beta02")

    implementation(Dependencies.Android.datastore)

    implementation(project(":core"))
    implementation(project(":theme"))

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)
}