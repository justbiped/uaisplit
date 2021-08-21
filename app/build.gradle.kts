plugins {
    id(Plugins.Android.application)
    id(Plugins.Kotlin.android)
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
            buildConfigField("String", "BASE_URL", "\"http://127.0.0.1:8080/\"")
        }

        getByName("internal") {
            applicationIdSuffix = ".internal"
            signingConfig = signingConfigs.getByName("production")

            buildConfigField("String", "BASE_URL", "\"https://hotmart-mobile-app.herokuapp.com/\"")
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

    implementation(project(":core"))

    localImplementation(Dependencies.Test.androidxCore)
    localImplementation(Dependencies.Test.fragment)
    localImplementation(Dependencies.Test.navigation)
    localImplementation(Dependencies.Test.mockServer)
    localImplementation(project(":core-tests"))

    testImplementation(Dependencies.Test.mockk)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.androidxCore)
    testImplementation(Dependencies.Test.androidxJunit)
    testImplementation(Dependencies.Test.archCore)
    testImplementation(Dependencies.Test.navigation)
    testImplementation(Dependencies.Test.runner)
    testImplementation(Dependencies.Test.espresso)
    testImplementation(Dependencies.Test.espressoContrib)
    testImplementation(Dependencies.Test.robolectric)
    testImplementation(Dependencies.Test.assertJ)
    kaptTest(Dependencies.daggerCompiler)

    androidTestImplementation(Dependencies.Test.mockkAndroid)
    androidTestImplementation(Dependencies.Test.androidxJunit)
    androidTestImplementation(Dependencies.Test.navigation)
    androidTestImplementation(Dependencies.Test.runner)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.espressoContrib)
    androidTestImplementation(Dependencies.Test.robolectricAnnotations)
    androidTestImplementation(Dependencies.Test.assertJ)
    kaptAndroidTest(Dependencies.daggerCompiler)
}