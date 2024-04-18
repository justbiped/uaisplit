plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
}

android {
    namespace = "biped.works.coroutines.test"
}
dependencies {
    implementation(libs.coroutine.test)
    implementation(libs.test.truth)
    implementation(project(":coroutines:core"))
}