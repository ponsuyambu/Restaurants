package challenge.android.gradleplugins.extensions

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

const val COMPILE_SDK = 30
const val TARGET_SDK = 30

fun Project.configureAndroid(additionalBlock: ((androidExtension: BaseExtension) -> Unit)? = null) =
    this.extensions.getByType(BaseExtension::class.java).run {
        compileSdkVersion(COMPILE_SDK)

        sourceSets {
            map { it.java.srcDir("src/${it.name}/kotlin") }
            this.getByName("test").java.srcDir("src/sharedTest/kotlin")
            this.getByName("androidTest").java.srcDir("src/sharedTest/kotlin")
        }

        defaultConfig {
            minSdkVersion(21)
            targetSdkVersion(TARGET_SDK)
            versionCode = 2
            versionName = "1.0.1"
            testInstrumentationRunner =
                "challenge.android.instrumentation.testutils.CustomTestRunner"
            consumerProguardFiles("consumer-rules.pro")

            lintOptions {
                isAbortOnError = true
                isWarningsAsErrors = true
                // Disabling below ids, because Timber causes this errors which we have no control over
                disable("UnsafeExperimentalUsageError", "UnsafeExperimentalUsageWarning")
            }

            packagingOptions {
                exclude("META-INF/**")
                exclude("META-INF/data_debug.kotlin_module")
                exclude("META-INF/ui_debug.kotlin_module")
                exclude("META-INF/domain_debug.kotlin_module")
                exclude("META-INF/view-model_debug.kotlin_module")
                exclude("win32-x86-64/attach_hotspot_windows.dll")
                exclude("win32-x86/attach_hotspot_windows.dll")
            }
        }

        buildTypes {
            getByName("debug") {
                isTestCoverageEnabled = true
            }
        }

        @Suppress("UnstableApiUsage")
        if (this is LibraryExtension) buildFeatures {
            dataBinding = true
        }

        @Suppress("UnstableApiUsage")
        if (this is BaseAppModuleExtension) buildFeatures {
            dataBinding = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        tasks.withType(KotlinCompile::class.java).configureEach {
            kotlinOptions {
                (this as KotlinJvmOptions).jvmTarget = "1.8"
            }
        }
        additionalBlock?.let {
            additionalBlock(this)
        }
    }

fun Project.configureRepos() {
    repositories {
        google()
        mavenCentral()
        jcenter() // Why => Barista dependency is still in jcenter. https://github.com/AdevintaSpain/Barista/issues/382
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

fun Project.commonAndroidPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    plugins.apply("dagger.hilt.android.plugin")
}
