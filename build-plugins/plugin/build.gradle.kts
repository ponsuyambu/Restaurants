plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
}
gradlePlugin {
    plugins.register("challenge.android.application") {
        id = "challenge.android.application"
        implementationClass = "challenge.android.gradleplugins.plugins.android.Application"
    }

    plugins.register("challenge.module.android.framework") {
        id = "challenge.module.android.framework"
        implementationClass =
            "challenge.android.gradleplugins.plugins.android.AndroidFrameworkModule"
    }

    plugins.register("challenge.module.android.presentation") {
        id = "challenge.module.android.presentation"
        implementationClass = "challenge.android.gradleplugins.plugins.android.PresentationModule"
    }

    plugins.register("challenge.module.data") {
        id = "challenge.module.data"
        implementationClass = "challenge.android.gradleplugins.plugins.kotlin.DataModule"
    }

    plugins.register("challenge.module.domain") {
        id = "challenge.module.domain"
        implementationClass = "challenge.android.gradleplugins.plugins.kotlin.DomainModule"
    }

    plugins.register("challenge.module.kotlin") {
        id = "challenge.module.kotlin"
        implementationClass = "challenge.android.gradleplugins.plugins.kotlin.KotlinModule"
    }

    plugins.register("challenge.module.usecase") {
        id = "challenge.module.usecase"
        implementationClass = "challenge.android.gradleplugins.plugins.kotlin.UseCaseModule"
    }
}
dependencies {

    /* Depend on the android gradle plugin */
    implementation("com.android.tools.build:gradle:" + project.properties["android.gradle.plugin"])

    /* Depend on the kotlin plugin*/
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:" + project.properties["kotlin.version"])
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.37")

    /* Depend on the default Gradle API's */
    implementation(gradleApi())
    implementation(localGroovy())
}
