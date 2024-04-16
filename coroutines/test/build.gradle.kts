plugins {
    id(Plugins.library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "biped.works.coroutines.test"
}
dependencies {
    implementation(libs.coroutine.test)
    implementation(libs.test.truth)
    implementation(project(":coroutines:core"))
}