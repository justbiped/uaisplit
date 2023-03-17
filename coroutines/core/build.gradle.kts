plugins {
    id(Plugins.library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "biped.works.coroutines"
}
dependencies {
    implementation(Dependencies.Coroutines.android)
    implementation("javax.inject:javax.inject:1")
}