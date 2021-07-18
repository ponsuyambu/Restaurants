package challenge.android.gradleplugins.plugins.android

import challenge.android.gradleplugins.Dependencies
import challenge.android.gradleplugins.Dependencies.UnitTesting.ANDROIDX_ARCH_CORE
import challenge.android.gradleplugins.extensions.androidMaterialDesign
import challenge.android.gradleplugins.extensions.androidXActivity
import challenge.android.gradleplugins.extensions.appModules
import challenge.android.gradleplugins.extensions.cardView
import challenge.android.gradleplugins.extensions.commonAndroidHilt
import challenge.android.gradleplugins.extensions.commonAndroidPlugins
import challenge.android.gradleplugins.extensions.configureAndroid
import challenge.android.gradleplugins.extensions.configureRepos
import challenge.android.gradleplugins.extensions.constraintLayout
import challenge.android.gradleplugins.extensions.implementation
import challenge.android.gradleplugins.extensions.recyclerView
import challenge.android.gradleplugins.extensions.shimmer
import challenge.android.gradleplugins.extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

open class PresentationModule : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            plugins.apply("com.android.library")
            commonAndroidPlugins()
            configureAndroid()
            configureRepos()
            dependencies.run {
                commonAndroidHilt()
                with(appModules()) {
                    common()
                    testUtils()
                    instrumentationUtils()
                    constraintLayout() // ui
                    cardView() // ui
                    recyclerView() // ui
                    androidMaterialDesign() // ui
                    androidXActivity() // ui
                    viewModelAndLiveData() // ui, view-model
                    shimmer() // ui
                }
                testImplementation(ANDROIDX_ARCH_CORE)
            }
        }
    }
}

fun DependencyHandler.viewModelAndLiveData() {
    implementation(Dependencies.AndroidX.LifeCycle.VIEW_MODEL)
    implementation(Dependencies.AndroidX.LifeCycle.LIVE_DATA)
    implementation(Dependencies.AndroidX.LifeCycle.SAVED_STATE)
    implementation(Dependencies.AndroidX.LifeCycle.EXTENSIONS)
}
