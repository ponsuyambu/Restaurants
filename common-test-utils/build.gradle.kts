import challenge.android.gradleplugins.Dependencies

plugins {
    id("challenge.module.kotlin")
}

dependencies {
    Dependencies.UnitTesting.KOTLIN_MODULE_LIBRARIES.forEach {
        api(it)
    }
}
