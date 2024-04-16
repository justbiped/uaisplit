plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
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