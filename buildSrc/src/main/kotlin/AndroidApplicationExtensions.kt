import com.android.build.api.dsl.ApplicationBuildType
import org.gradle.api.NamedDomainObjectContainer

const val local = "local"
const val internal = "internal"
const val production = "production"

fun NamedDomainObjectContainer<ApplicationBuildType>.local(config: ApplicationBuildType.() -> Unit) {
    maybeCreate(local)
    getByName(local) {
        initWith(getByName("debug"))
        config(this)
    }
}

fun NamedDomainObjectContainer<ApplicationBuildType>.internal(config: ApplicationBuildType.() -> Unit) {
    maybeCreate(internal)
    getByName(internal) {
        config(this)
    }
}

fun NamedDomainObjectContainer<ApplicationBuildType>.production(config: ApplicationBuildType.() -> Unit) {
    maybeCreate(production)
    getByName(production) {
        config(this)
    }

    getByName(internal) {
        initWith(getByName(production))
        buildConfigField("Boolean", "DEBUG", "true")
        isDebuggable = true
    }
}
