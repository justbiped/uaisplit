import com.android.build.api.dsl.LibraryBuildType
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<LibraryBuildType>.local(config: LibraryBuildType.() -> Unit = {}) {
    maybeCreate(local)
    getByName(local) {
        initWith(getByName("debug"))
        config(this)
    }
}

fun NamedDomainObjectContainer<LibraryBuildType>.internal(config: LibraryBuildType.() -> Unit) {
    maybeCreate(internal)
    getByName(internal) {
        config(this)
    }
}

fun NamedDomainObjectContainer<LibraryBuildType>.production(config: LibraryBuildType.() -> Unit) {
    getByName(production) {
        config(this)
    }

    getByName(internal) {
        initWith(getByName(production))
        buildConfigField("Boolean", "DEBUG", "true")
    }
}