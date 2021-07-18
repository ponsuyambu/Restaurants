package challenge.android.gradleplugins.extensions

import challenge.android.gradleplugins.Dependencies
import challenge.android.gradleplugins.Dependencies.AndroidX
import challenge.android.gradleplugins.Dependencies.AndroidX.APPCOMPAT
import challenge.android.gradleplugins.Dependencies.AndroidX.CARD_VIEW
import challenge.android.gradleplugins.Dependencies.AndroidX.CONSTRAINT_LAYOUT
import challenge.android.gradleplugins.Dependencies.AndroidX.CORE
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

fun DependencyHandler.api(dependencyNotation: Any) {
    add("api", dependencyNotation)
}

fun DependencyHandler.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}

fun DependencyHandler.testImplementation(dependencyNotation: Any) {
    add("testImplementation", dependencyNotation)
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}

fun DependencyHandler.kaptAndroidTest(dependencyNotation: Any) {
    add("kaptAndroidTest", dependencyNotation)
}

fun DependencyHandler.commonAndroidHilt() {
    api(Dependencies.Hilt.ANDROID)
    kapt(Dependencies.Hilt.COMPILER)
    kapt(Dependencies.Hilt.COMPILER_ANDROIDX)
    kaptAndroidTest(Dependencies.Hilt.COMPILER)
}

fun DependencyHandler.shimmer() {
    implementation(Dependencies.SHIMMER)
}

fun DependencyHandler.androidXCore() {
    api(CORE)
    api(APPCOMPAT)
}

fun DependencyHandler.androidMaterialDesign() {
    implementation(Dependencies.Google.MATERIAL_DESIGN)
}

fun DependencyHandler.constraintLayout() {
    implementation(CONSTRAINT_LAYOUT)
}

fun DependencyHandler.cardView() {
    api(CARD_VIEW)
}

fun DependencyHandler.recyclerView() {
    api(AndroidX.RECYCLER_VIEW)
}

fun DependencyHandler.androidXActivity() {
    api(AndroidX.Ktx.ACTIVITY)
    api(AndroidX.Ktx.FRAGMENT)
}

fun DependencyHandler.coroutines() {
    api(Dependencies.Coroutines.CORE)
    api(Dependencies.Coroutines.ANDROID)
}

fun DependencyHandler.retrofit() {
    api(Dependencies.Retrofit.CORE)
    api(Dependencies.Retrofit.GSON_CONVERTER)
    api(Dependencies.Retrofit.LOGGING_INTERCEPTOR)
}

fun DependencyHandler.timber() {
    api(Dependencies.TIMBER)
}

fun DependencyHandler.kotlinLibrary() {
    api(Dependencies.KOTLIN_LIB)
}

fun DependencyHandler.espressoIdlingResource() {
    api(Dependencies.AndroidTesting.Espresso.IDLING_RESOURCES)
}

fun DependencyHandler.gson() {
    api(Dependencies.Google.GSON)
}

fun DependencyHandler.testUtilsProject() {
    testImplementation(project(":common-test-utils"))
}

fun DependencyHandler.appModules() = Modules(this)

class Module(private val dependencyHandler: DependencyHandler, private val modulePrefix: String) {
    fun ui() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:ui"))
        }
    }

    fun presentation() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:presentation"))
        }
    }

    fun useCase() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:use-case"))
        }
    }

    fun framework() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:framework"))
        }
    }

    fun domain() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:domain"))
        }
    }

    fun data() {
        dependencyHandler.run {
            implementation(project(":$modulePrefix:data"))
        }
    }
}

class Modules(private val dependencyHandler: DependencyHandler) {

    val featureRestaurantList = Module(dependencyHandler, "feature-restaurant-list")

    fun common() {
        dependencyHandler.run {
            api(project(":common"))
        }
    }

    fun testUtils() {
        dependencyHandler.run {
            testImplementation(project(":common-test-utils"))
        }
    }

    fun instrumentationUtils() {
        dependencyHandler.run {
            androidTestImplementation(project(":common-instrumentation-test-utils"))
        }
    }
}
