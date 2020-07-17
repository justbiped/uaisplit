plugins {
    id(Plugins.androidApplication)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.androidExtensions)
    id(Plugins.Kotlin.kapt)
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080/\"")
        }

        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:8080/\"")
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.coroutinesAndroid)

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appCompat)
    implementation(Dependencies.materialDesign)

    implementation(Dependencies.Androidx.constraintLayout)

    implementation(Dependencies.Androidx.activity)
    implementation(Dependencies.Androidx.fragment)

    implementation(Dependencies.Androidx.navigationFragment)
    implementation(Dependencies.Androidx.navigationUI)

    implementation(Dependencies.Androidx.lifecycleLiveData)
    implementation(Dependencies.Androidx.lifecycleViewModel)
    implementation(Dependencies.Androidx.lifecycleExtensions)

    testImplementation(Dependencies.Test.jUnit)
    testImplementation(Dependencies.Test.mockitoInline)
    testImplementation(Dependencies.Test.mockitoKotlin)
    testImplementation(Dependencies.Test.assertJ)

    testImplementation(Dependencies.Test.androidxCore)
    testImplementation(Dependencies.Test.androidxJunit)

    testImplementation(Dependencies.Test.robolectric)

    testImplementation(Dependencies.Test.archCore)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.fragment)

    androidTestImplementation(Dependencies.Test.runner)
    androidTestImplementation(Dependencies.Test.espresso)

    kaptTest(Dependencies.daggerCompiler)
}