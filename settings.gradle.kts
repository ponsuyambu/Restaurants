import java.io.FileInputStream
import java.util.Properties

val moduleProperties =
    Properties().apply { load(FileInputStream("$rootDir/build.config.properties")) }

rootProject.name = "Restaurants"
loadFeatureModules(moduleProperties["feature-modules"])
loadModules(moduleProperties["other-modules"])
loadModules(moduleProperties["app-module"])

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
}

includeBuild("build-plugins") {
    dependencySubstitution {
        substitute(module("challenge.plugins:gradle-plugin")).with(project(":plugin"))
    }
}

fun loadFeatureModules(config: Any?) {
    config?.toString()?.split(",")?.map { it.trim() }?.forEach {
        include(":$it:presentation")
        include(":$it:use-case")
        include(":$it:domain")
        include(":$it:data")
        include(":$it:framework")
    }
}

fun loadModules(config: Any?) {
    config?.toString()?.split(",")?.map { it.trim() }?.forEach {
        include(":$it")
    }
}
