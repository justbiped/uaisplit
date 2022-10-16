plugins {
    id(Plugins.application)
    id(Plugins.hilt)
    id(Plugins.serialization)
    id(Plugins.kapt)
    id(Plugins.compose)
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("production") {
            storeFile = File("${rootProject.rootDir}/location_key_prod")
            storePassword = "1a]IKErY0g]!\\K)(v"
            keyAlias = "locaitons"
            keyPassword = "1a]IKErY0g]!\\K)(v"
        }
    }

    buildTypes {
        local {
            applicationIdSuffix = ".local"
        }

        production {
            signingConfig = signingConfigs.getByName("production")
            buildConfigField("String", "BASE_URL", "\"https://hotmart-mobile-app.herokuapp.com/\"")
        }
    }
}

dependencies {
    implementation(Dependencies.Coroutines.android)

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.materialDesign)

    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)

    implementation(Dependencies.Android.constraintLayout)

    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Fragment.core)

    implementation(Dependencies.Navigator.fragment)
    implementation(Dependencies.Navigator.ui)

    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Compose.coil)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.httpLogging)
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(project(":location"))
    implementation(project(":profile"))
    implementation(project(":statement"))
    implementation(project(":settings"))
    implementation(project(":core"))
    implementation(project(":theme"))
    localImplementation(project(":test"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}
