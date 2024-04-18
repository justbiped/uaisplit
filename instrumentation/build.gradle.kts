plugins {
    apply(libs.plugins.android.library)
    apply(libs.plugins.kotlin.android)
}

android {
    defaultConfig {
        namespace = "com.biped.locations.instrumentation"
        testApplicationId = "com.biped.locations.instrumentation"
    }
}

dependencies {
    androidTestImplementation(project(":foundation:test"))
    androidTestImplementation(libs.android.test.uiautomator)
}