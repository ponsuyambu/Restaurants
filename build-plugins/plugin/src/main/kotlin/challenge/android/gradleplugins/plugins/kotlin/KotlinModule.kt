package challenge.android.gradleplugins.plugins.kotlin

import challenge.android.gradleplugins.Dependencies
import challenge.android.gradleplugins.Dependencies.UnitTesting.COROUTINES
import challenge.android.gradleplugins.Dependencies.UnitTesting.JUNIT
import challenge.android.gradleplugins.Dependencies.UnitTesting.MOCKK
import challenge.android.gradleplugins.extensions.configureRepos
import challenge.android.gradleplugins.extensions.coroutines
import challenge.android.gradleplugins.extensions.implementation
import challenge.android.gradleplugins.extensions.kapt
import challenge.android.gradleplugins.extensions.kotlinLibrary
import challenge.android.gradleplugins.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

open class KotlinModule : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("java-library")
            plugins.apply("org.jetbrains.kotlin.jvm")
            plugins.apply("kotlin-kapt")
            configureRepos()
            dependencies {
                kotlinLibrary()
                coroutines()
                implementation(Dependencies.Hilt.CORE)
                kapt(Dependencies.Hilt.COMPILER)
                testImplementation(MOCKK)
                testImplementation(JUNIT)
                testImplementation(COROUTINES)
            }
        }
    }
}
