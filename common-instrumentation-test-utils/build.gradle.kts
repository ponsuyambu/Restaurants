import challenge.android.gradleplugins.Dependencies
import challenge.android.gradleplugins.extensions.api
import challenge.android.gradleplugins.extensions.appModules

plugins {
    id("challenge.module.android.framework")
}

dependencies {
    appModules().common()
    Dependencies.AndroidTesting.all().forEach {
        api(it)
    }
    api(Dependencies.AndroidTesting.BARISTA) {
        exclude(group = "org.jetbrains.kotlin")
    }
}
