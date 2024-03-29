plugins {
    id(Plugins.library)
    id(Plugins.kotlin_android)
}

android {
    namespace = "biped.works.coroutines.test"
}
dependencies {
    implementation(Dependencies.Coroutines.test)
    implementation(Dependencies.Test.truth)
    implementation(project(":coroutines:core"))
}