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
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}
dependencies {
    implementation(platform(libs.compose.bom))

    implementation(libs.coroutine.android)
    implementation(libs.lifecycle.runtime)

    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.windowSizeClass)
    implementation(libs.compose.icons)
    implementation(libs.compose.iconsExtended)
    implementation(libs.compose.animation)
    implementation(libs.compose.coil)

    implementation(libs.compose.navigation)

    implementation(libs.compose.ui.tooling.preview)
    devImplementation(libs.compose.ui.tooling)

    testImplementation(libs.compose.test.junit)
    devImplementation(libs.compose.test.manifest)
}