plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
    id(Plugins.compose)
}

dependencies {
    implementation(Dependencies.Lifecycle.runtime)
    implementation(Dependencies.Android.materialDesign)
}