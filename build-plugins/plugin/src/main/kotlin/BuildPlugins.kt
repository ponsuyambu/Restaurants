import org.gradle.kotlin.dsl.DependencyHandlerScope

object BuildPlugins {
    private const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
    private const val ANDROID_GRADLE = "com.android.tools.build:gradle:4.2.1"
    private const val HILT = "com.google.dagger:hilt-android-gradle-plugin:2.37"

    private val plugins = listOf(KOTLIN_GRADLE, ANDROID_GRADLE, HILT)

    fun apply(dependencyHandlerScope: DependencyHandlerScope) {
        plugins.forEach {
            dependencyHandlerScope.dependencies.add("classpath", it)
        }
    }
}
