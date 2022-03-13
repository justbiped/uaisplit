plugins {
    id(Plugins.Android.library)
    id(Plugins.Android.hilt)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.serialization)
}

android {
    buildTypes {
        local {
            buildConfigField("String", "BASE_URL", "\"http://127.0.0.1:8080/\"")
        }

        production {
            buildConfigField("String", "BASE_URL", "\"https://hotmart-mobile-app.herokuapp.com/\"")
        }
    }

    instrumentation {
        hasManagedDevice = true
    }
}

dependencies {
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Android.fragment)
    implementation(Dependencies.Android.navigationFragment)
    implementation(Dependencies.Android.navigationUI)
    implementation(Dependencies.Android.lifecycleLiveData)
    implementation(Dependencies.Android.lifecycleViewModel)

    implementation(Dependencies.coil)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serialization)

    implementation(Dependencies.Android.hilt)
    kapt(Dependencies.Android.hiltCompiler)

    implementation(project(":core"))
    localImplementation(project(":core:test"))

    kaptTest(Dependencies.Android.hiltCompiler)
    kaptAndroidTest(Dependencies.Android.hiltCompiler)
}
