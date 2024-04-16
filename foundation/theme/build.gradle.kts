plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.compose)
}

dependencies {
    implementation(libs.lifecycle.runtime)
    implementation(Dependencies.Android.materialDesign)
}