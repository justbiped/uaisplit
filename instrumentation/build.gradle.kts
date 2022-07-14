plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {
    defaultConfig {
        testApplicationId = "com.biped.locations.instrumentation"
    }
}

dependencies {
    androidTestImplementation(project(":core-test"))
    androidTestImplementation(Dependencies.Test.uiAutomator)
}