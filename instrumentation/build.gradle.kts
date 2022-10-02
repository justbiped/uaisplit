plugins {
    id(Plugins.Library)
    id(Plugins.Android)
}

android {
    defaultConfig {
        testApplicationId = "com.biped.locations.instrumentation"
    }
}

dependencies {
    androidTestImplementation(project(":test"))
    androidTestImplementation(Dependencies.Test.uiAutomator)
}