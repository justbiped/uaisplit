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
include(":instrumentation")
include(":location")
include(":profile")
include(":settings")
include(":statement")

include(":foundation")
include(":foundation:core")
include(":foundation:test")
include(":foundation:theme")

include(":coroutines")
include(":coroutines:core")
include(":coroutines:test")

project(":statement").projectDir = File(rootDir, "features/statement")
project(":profile").projectDir = File(rootDir, "features/profile")
project(":settings").projectDir = File(rootDir, "features/settings")
project(":location").projectDir = File(rootDir, "features/location")
