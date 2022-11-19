plugins {
    id(Plugins.library)
    id(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.compose)
}

android {
    namespace = "biped.works.tosplit.statement"
}

dependencies {
    implementation(Dependencies.Kotlin.core)
    implementation(Dependencies.Android.appCompat)

    implementation(project(":foundation:core"))
    implementation(project(":foundation:theme"))

    implementation(project(":coroutines:core"))
    testImplementation(project(":coroutines:test"))

    implementation(Dependencies.Hilt.core)
    kapt(Dependencies.Hilt.compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}