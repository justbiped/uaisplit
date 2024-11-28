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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":instrumentation")

include(":transaction")
include(":transaction:transaction")
include(":transaction:statement")

include(":features")
include(":features:user")
include(":features:profile")
include(":features:settings")


include(":foundation")
include(":foundation:core")
include(":foundation:test")
include(":foundation:theme")
include(":foundation:compose")

include(":database")

include(":coroutines")
include(":coroutines:core")
include(":coroutines:test")

include("automation")
include(":foundation:api")
