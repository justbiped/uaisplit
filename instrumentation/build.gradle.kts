plugins {
    id(Plugins.Android.test)
    id(Plugins.Kotlin.android)
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 30

        testApplicationId = "com.favoriteplaces.instrumentation"
        testInstrumentationRunner = "com.favoriteplaces.core.test.instrumentation.runner.LocationTestRunner"
        testInstrumentationRunnerArguments["class"] = "com.favoriteplaces.instrumentation.InstrumentationTestSuit"
    }

    buildTypes {
        create(local) {
            initWith(getByName("debug"))
        }
    }

    variantFilter {
        if (buildType.name.contains("debug")) ignore = true
    }

    targetProjectPath = ":app"
}

dependencies {
    implementation(project(":core:test"))

    implementation(Dependencies.Test.assertJ)
    implementation(Dependencies.Test.androidxCore)
    implementation(Dependencies.Test.runner)
    implementation(Dependencies.Test.espresso)
    implementation(Dependencies.Test.uiAutomator)
}