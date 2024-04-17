plugins {
    id(Plugins.library)
    apply(libs.plugins.kotlin.android)
    id(Plugins.compose)
}

android {
    buildFeatures {
        namespace = "com.biped.locations.theme"
    }
}

dependencies {
    implementation(libs.lifecycle.runtime)
    implementation(Dependencies.Android.materialDesign)
}