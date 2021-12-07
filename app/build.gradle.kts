plugins {
    id(Plugins.Android.application)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.serialization)
}

android {

    defaultConfig {
        testInstrumentationRunner = "com.favoriteplaces.LocationTestRunner"
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

        getByName("local") {
            applicationIdSuffix = ".local"
        }

        getByName("internal") {
            applicationIdSuffix = ".internal"
            signingConfig = signingConfigs.getByName("production")
        }

        getByName("production") {
            signingConfig = signingConfigs.getByName("production")

            buildConfigField("String", "BASE_URL", "\"https://hotmart-mobile-app.herokuapp.com/\"")
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appCompat)
    implementation(Dependencies.Androidx.materialDesign)

    implementation(Dependencies.Androidx.constraintLayout)

    implementation(Dependencies.Androidx.activity)
    implementation(Dependencies.Androidx.fragment)

    implementation(Dependencies.Androidx.navigationFragment)
    implementation(Dependencies.Androidx.navigationUI)

    implementation(Dependencies.Androidx.lifecycleLiveData)
    implementation(Dependencies.Androidx.lifecycleViewModel)

    implementation(Dependencies.coil)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.serializationConverter)
    implementation(Dependencies.httpLogging)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.serialization)

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)

    implementation(project(":location"))
    implementation(project(":core"))

    localImplementation(project(":core-tests"))
    kaptTest(Dependencies.daggerCompiler)
    kaptAndroidTest(Dependencies.daggerCompiler)
}