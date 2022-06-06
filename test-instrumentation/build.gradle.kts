plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {
    defaultConfig {
        testApplicationId = "com.favoriteplaces.instrumentation"

        testInstrumentationRunnerArguments["class"] =
            "com.favoriteplaces.instrumentation.InstrumentationTestSuite"
    }
}

dependencies {
    androidTestImplementation(project(":core:test"))
    androidTestImplementation(Dependencies.Test.uiAutomator)
}