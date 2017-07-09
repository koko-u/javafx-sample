package jp.co.kokou.junit5.ext

import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import org.junit.platform.gradle.plugin.*

// extension for configuration
fun JUnitPlatformExtension.filters(setup: FiltersExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType<FiltersExtension>().setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
fun JUnitPlatformExtension.selectors(setup: SelectorsExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType<SelectorsExtension>().setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
fun FiltersExtension.engines(setup: EnginesExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType<EnginesExtension>().setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
fun FiltersExtension.tags(setup: TagsExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType<TagsExtension>().setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}
fun FiltersExtension.packages(setup: PackagesExtension.() -> Unit) {
    when (this) {
        is ExtensionAware -> extensions.getByType<PackagesExtension>().setup()
        else -> throw Exception("${this::class} must be an instance of ExtensionAware")
    }
}

inline fun <reified T> ExtensionContainer.getByType(): T =
        this.getByType(T::class.java)
