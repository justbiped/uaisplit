plugins {
    id(Plugins.Android.library)
    id(Plugins.Kotlin.android)
}

android {

}

dependencies {
    implementation(Dependencies.Test.coroutines)
    implementation(Dependencies.Test.archCore)
    implementation(project(":core"))
}
