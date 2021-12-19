package androidext

import com.android.build.api.dsl.ApplicationBuildType
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<ApplicationBuildType>.local(config: ApplicationBuildType.() -> Unit) {
    getByName("local") {
        initWith(getByName("debug"))
        config(this)
    }
}

fun NamedDomainObjectContainer<ApplicationBuildType>.internal(config: ApplicationBuildType.() -> Unit) {
    getByName("internal") {
        config(this)
    }
}

fun NamedDomainObjectContainer<ApplicationBuildType>.production(config: ApplicationBuildType.() -> Unit) {
    getByName("production") {
        config(this)
    }

    getByName("internal") {
        initWith(getByName("production"))
        buildConfigField("Boolean", "DEBUG", "true")
        isDebuggable = true
    }
}
