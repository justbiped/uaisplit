plugins {
    id(Plugins.Android.application)
    id(Plugins.Android.hilt)
    id(Plugins.Kotlin.serialization)
    id(Plugins.Kotlin.kapt)
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
    implementation(Dependencies.serializationConverter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.serialization)

    implementation(Dependencies.Android.hilt)
    kapt(Dependencies.Android.hiltCompiler)

    implementation(project(":location"))
    implementation(project(":profile"))
    implementation(project(":core"))
    localImplementation(project(":core:test"))

    kaptTest(Dependencies.Android.hiltCompiler)
    kaptAndroidTest(Dependencies.Android.hiltCompiler)
}
