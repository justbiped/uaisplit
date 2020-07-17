object Versions {
    const val kotlin = "1.3.72"
    const val navigation = "2.2.2"
    const val lifecycle = "2.2.0"
    const val retrofit = "2.6.1"
    const val dagger = "2.25.3"
}

object Dependencies {
    object Androidx {
        const val core = "androidx.core:core-ktx:1.2.0"
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val activity = "androidx.activity:activity-ktx:1.1.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.4"

        const val lifecycleLiveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Kotlin {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    }

    const val materialDesign = "com.google.android.material:material:1.1.0"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val httpLogging = "com.squareup.okhttp3:logging-interceptor:3.8.0"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    object Test {
        const val jUnit = "junit:junit:4.13"
        const val mockitoInline = "org.mockito:mockito-inline:2.28.2"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        const val assertJ = "org.assertj:assertj-core:3.13.2"

        const val androidxCore = "androidx.test:core-ktx:1.2.0"
        const val androidxJunit = "androidx.test.ext:junit-ktx:1.1.1"

        const val archCore = "android.arch.core:core-testing:1.1.1"

        const val robolectric = "org.robolectric:robolectric:4.3"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3"
        const val fragment = "androidx.fragment:fragment-testing:1.2.4"

        const val runner = "androidx.test:runner:1.2.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    }
}

object Plugins {

    object Kotlin {
        const val android = "kotlin-android"
        const val androidExtensions = "kotlin-android-extensions"
        const val kapt: String = "kotlin-kapt"
    }

    const val googleServices = "com.google.gms.google-services"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
}

object Path {
    const val androidGradle = "com.android.tools.build:gradle:4.0.0"
    const val playServices = "com.google.gms:google-services:4.3.3"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}