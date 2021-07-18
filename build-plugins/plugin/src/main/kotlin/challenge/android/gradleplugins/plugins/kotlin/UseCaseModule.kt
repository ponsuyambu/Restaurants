package challenge.android.gradleplugins.plugins.kotlin

import challenge.android.gradleplugins.extensions.testUtilsProject
import org.gradle.api.Project

open class UseCaseModule : KotlinModule() {
    override fun apply(project: Project) {
        super.apply(project)
        with(project.dependencies) {
            testUtilsProject()
        }
    }
}
