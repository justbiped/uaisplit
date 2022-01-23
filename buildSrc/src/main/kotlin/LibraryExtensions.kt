import com.android.build.api.dsl.LibraryBuildType
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<LibraryBuildType>.local(config: LibraryBuildType.() -> Unit) {
    getByName("local") {
        initWith(getByName("debug"))
        config(this)
    }
}

fun NamedDomainObjectContainer<LibraryBuildType>.internal(config: LibraryBuildType.() -> Unit) {
    getByName("internal") {
        config(this)
    }
}

fun NamedDomainObjectContainer<LibraryBuildType>.production(config: LibraryBuildType.() -> Unit) {
    getByName("production") {
        config(this)
    }

    getByName("internal") {
        initWith(getByName("production"))
        buildConfigField("Boolean", "DEBUG", "true")
    }
}
