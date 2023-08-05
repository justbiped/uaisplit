plugins {
    id(Plugins.library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "biped.works.compose"

    buildFeatures.apply {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compiler_vesion
    }
}
dependencies {
    implementation(platform(Dependencies.Compose.bom))

    implementation(Dependencies.Coroutines.android)
    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.material_window)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Navigation.compose)

    implementation(Dependencies.Compose.tooling_preview)
    devImplementation(Dependencies.Compose.tooling)

    testImplementation(Dependencies.Compose.test_junit)
    devImplementation(Dependencies.Compose.test_manifest)
}