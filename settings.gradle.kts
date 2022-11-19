pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
include(":core")
include(":instrumentation")
include(":location")
include(":test")
include(":profile")
include(":theme")
include(":settings")
include(":statement")

include(":coroutines")
include(":coroutines:core")
include(":coroutines:test")

project(":statement").projectDir = File(rootDir, "features/statement")
project(":profile").projectDir = File(rootDir, "features/profile")
project(":settings").projectDir = File(rootDir, "features/settings")
project(":location").projectDir = File(rootDir, "features/location")

project(":core").projectDir = File(rootDir, "foundation/core")
project(":test").projectDir = File(rootDir, "foundation/test")
project(":theme").projectDir = File(rootDir, "foundation/theme")
