package challenge.android.gradleplugins.plugins.android

import challenge.android.gradleplugins.extensions.commonAndroidHilt
import challenge.android.gradleplugins.extensions.commonAndroidPlugins
import challenge.android.gradleplugins.extensions.configureAndroid
import challenge.android.gradleplugins.extensions.configureRepos
import org.gradle.api.Plugin
import org.gradle.api.Project

open class Application : Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            plugins.apply("com.android.application")
            commonAndroidPlugins()
            configureAndroid()
            configureRepos()
            dependencies.run {
                commonAndroidHilt()
            }
        }
    }
}
