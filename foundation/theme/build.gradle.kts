plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
    apply(libs.plugins.compose)
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