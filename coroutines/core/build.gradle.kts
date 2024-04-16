plugins {
    id(Plugins.library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "biped.works.coroutines"
}
dependencies {
    implementation(libs.coroutine.android)
    implementation("javax.inject:javax.inject:1")
}