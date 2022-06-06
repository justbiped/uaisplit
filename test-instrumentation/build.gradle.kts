plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {
    defaultConfig {
        testApplicationId = "com.favoriteplaces.instrumentation"
        testInstrumentationRunnerArguments["class"] =
            "com.favoriteplaces.instrumentation.InstrumentationTestSuit"
    }
}

dependencies {
    localImplementation(project(":core:test"))

    implementation(Dependencies.Test.uiAutomator)
}