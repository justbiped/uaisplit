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

include(":features")
include(":features:location")
include(":features:profile")
include(":features:settings")
include(":features:transaction")


include(":foundation")
include(":foundation:core")
include(":foundation:test")
include(":foundation:theme")
include(":foundation:compose")

include(":coroutines")
include(":coroutines:core")
include(":coroutines:test")