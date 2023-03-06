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

        namespace = "biped.works.locations"
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

    implementation(Dependencies.Compose.tooling_preview)
    devImplementation(Dependencies.Compose.tooling)

    implementation(Dependencies.Android.constraintLayout)

    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Fragment.core)

    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)

    implementation(Dependencies.Lifecycle.liveData)
    implementation(Dependencies.Lifecycle.viewModel)
    implementation(Dependencies.Lifecycle.runtime)

    implementation(Dependencies.Square.retrofit)
    implementation(Dependencies.Square.httpLogging)
    implementation(Dependencies.Square.okHttp)
    implementation(Dependencies.Serialization.converter)
    implementation(Dependencies.Serialization.core)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(project(":features:user"))
    implementation(project(":features:settings"))
    implementation(project(":features:profile"))
    implementation(project(":features:location"))
    implementation(project(":features:transaction"))

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))
    testImplementation(project(":foundation:test"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}
