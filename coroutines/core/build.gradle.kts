plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
}

android {
    namespace = "biped.works.coroutines"
}
dependencies {
    implementation(libs.coroutine.android)
    implementation("javax.inject:javax.inject:1")
}