plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
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