plugins {
    id(Plugins.Android.application)
    id(Dependencies.Hilt.plugin)
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
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

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.icons)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.pager)

    implementation(Dependencies.Compose.foundation)
    implementation(Dependencies.Compose.runtime)

    implementation(Dependencies.Compose.toolingPreview)
    devImplementation(Dependencies.Compose.tooling)

    implementation(Dependencies.Android.constraintLayout)

    implementation(Dependencies.Android.activity)
    implementation(Dependencies.Fragment.core)

    implementation(Dependencies.Navigator.fragment)
    implementation(Dependencies.Navigator.ui)

    implementation(Dependencies.Android.lifecycleLiveData)
    implementation(Dependencies.Android.lifecycleViewModel)

    implementation(Dependencies.coil)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serializationConverter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.serialization)

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    implementation(project(":location"))
    implementation(project(":profile"))
    implementation(project(":core"))
    implementation(project(":theme"))
    localImplementation(project(":test"))

    kaptTest(Dependencies.Hilt.compiler)
    kaptAndroidTest(Dependencies.Hilt.compiler)
}
