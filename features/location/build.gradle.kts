plugins {
    id(Plugins.Android.library)
    id(Dependencies.Hilt.plugin)
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
    implementation(Dependencies.Android.Fragment.fragment)
    implementation(Dependencies.Navigator.fragment)
    implementation(Dependencies.Navigator.ui)
    implementation(Dependencies.Android.lifecycleLiveData)
    implementation(Dependencies.Android.lifecycleViewModel)

    implementation(Dependencies.coil)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serialization)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(project(":core"))
    implementation(project(":theme"))
    "localImplementation"(project(":test"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}
