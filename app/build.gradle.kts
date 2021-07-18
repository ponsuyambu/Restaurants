import challenge.android.gradleplugins.extensions.appModules

plugins {
    id("challenge.android.application")
}

android {
    defaultConfig {
        applicationId("challenge.android")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    with(appModules()) {
        listOf(featureRestaurantList).forEach {
            it.presentation()
            it.useCase()
            it.domain()
            it.data()
            it.framework()
        }

        instrumentationUtils()
    }
}
