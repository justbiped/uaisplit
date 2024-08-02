plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
}

android {
    namespace = "biped.works.coroutines"
}
dependencies {
    implementation(libs.coroutine.android)
    implementation(libs.java.inject)
}