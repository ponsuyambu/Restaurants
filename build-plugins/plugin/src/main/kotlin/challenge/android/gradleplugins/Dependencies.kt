package challenge.android.gradleplugins

object Dependencies {

    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"
    const val KOTLIN_LIB = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.Kotlin
    const val SHIMMER = "com.facebook.shimmer:shimmer:0.5.0"

    object Coroutines {
        private const val coroutinesVersion = "1.3.6"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Hilt {
        const val CORE = "com.google.dagger:hilt-core:${Versions.Hilt}"
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.Hilt}"
        const val COMPILER_ANDROIDX = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object Google {
        const val MATERIAL_DESIGN = "com.google.android.material:material:1.3.0"
        const val GSON = "com.google.code.gson:gson:2.8.6"
    }

    object Retrofit {
        const val CORE = "com.squareup.retrofit2:retrofit:2.8.1"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:2.8.1"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.2.2"
    }

    object UnitTesting {
        const val JUNIT = "junit:junit:4.13.1"
        const val MOCKK = "io.mockk:mockk:1.11.0"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"

        val KOTLIN_MODULE_LIBRARIES = listOf(JUNIT, MOCKK, COROUTINES)

        const val ANDROIDX_ARCH_CORE = "androidx.arch.core:core-testing:2.1.0"
    }

    object AndroidTesting {
        const val MOCKK = "io.mockk:mockk-android:1.11.0"
        const val JUNIT_EXTENSIONS = "androidx.test.ext:junit:1.1.2"
        const val TEST_RUNNER = "androidx.test:runner:1.1.0"
        const val HILT = "com.google.dagger:hilt-android-testing:2.37"
        const val BARISTA = "com.schibsted.spain:barista:3.9.0"

        object Espresso {
            const val CORE = "androidx.test.espresso:espresso-core:3.3.0"
            const val INTENTS = "androidx.test.espresso:espresso-intents:3.1.0"
            const val IDLING_RESOURCES = "androidx.test.espresso:espresso-idling-resource:3.3.0"
        }

        fun all() = listOf(
            UnitTesting.JUNIT, UnitTesting.ANDROIDX_ARCH_CORE, MOCKK,
            TEST_RUNNER, JUNIT_EXTENSIONS, HILT, Espresso.CORE, Espresso.INTENTS
        )
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.2"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.3.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val CARD_VIEW = "androidx.cardview:cardview:1.0.0"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.0.0"

        object LifeCycle {
            private const val lifecycleVersion = "2.3.1"
            private const val lifecycleExtensionsVersion = "2.2.0"
            const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
            const val SAVED_STATE =
                "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"
            const val EXTENSIONS =
                "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionsVersion"
        }

        object Ktx {
            const val ACTIVITY = "androidx.activity:activity-ktx:1.2.0-alpha06"
            const val FRAGMENT = "androidx.fragment:fragment-ktx:1.2.5"
        }
    }
}
