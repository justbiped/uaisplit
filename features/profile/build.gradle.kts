plugins {
    id(Plugins.Android.library)
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
    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)

    implementation(Dependencies.Navigator.ui)
    implementation(Dependencies.Navigator.fragment)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.pager)

    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.runtime)

    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)

    implementation("io.coil-kt:coil-compose:2.1.0")

    implementation(project(":core"))
    implementation(project(":theme"))
}
