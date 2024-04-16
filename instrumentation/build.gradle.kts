plugins {
    id(Plugins.library)
    id(Plugins.kotlin)
}

android {
    defaultConfig {
        testApplicationId = "com.biped.locations.instrumentation"
    }
}

dependencies {
    androidTestImplementation(project(":foundation:test"))
    androidTestImplementation(libs.android.test.uiautomator)
}