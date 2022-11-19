plugins {
    id(Plugins.library)
    id(Plugins.hilt)
    id(Plugins.kapt)
    id(Plugins.serialization)
    id(Plugins.safe_args)
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
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Fragment.core)
    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)
    implementation(Dependencies.Lifecycle.viewModel)

    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(project(":core"))
    implementation(project(":theme"))
    devImplementation(project(":test"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}
