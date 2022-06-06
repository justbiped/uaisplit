plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 30

        testApplicationId = "com.favoriteplaces.instrumentation"
        testInstrumentationRunnerArguments["class"] = "com.favoriteplaces.instrumentation.InstrumentationTestSuit"
    }
}

dependencies {
    localImplementation(project(":core:test"))

//    implementation(Dependencies.Test.assertJ)
//    implementation(Dependencies.Test.androidxCore)
//    implementation(Dependencies.Test.runner)
//    implementation(Dependencies.Test.espresso)
    implementation(Dependencies.Test.uiAutomator)
}