import androidext.local
import androidext.production

plugins {
    id(Plugins.Android.library)
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
    implementation(Dependencies.serialization)

    implementation(Dependencies.dagger)
    implementation(Dependencies.daggerAndroid)
    implementation(Dependencies.daggerSupport)
    kapt(Dependencies.daggerCompiler)
    kapt(Dependencies.daggerProcessor)

    implementation(project(":core"))

    localImplementation(project(":core-tests"))
    kaptLocal(Dependencies.daggerCompiler)
}
