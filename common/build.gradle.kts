import challenge.android.gradleplugins.extensions.androidMaterialDesign
import challenge.android.gradleplugins.extensions.androidXCore
import challenge.android.gradleplugins.extensions.coroutines
import challenge.android.gradleplugins.extensions.espressoIdlingResource
import challenge.android.gradleplugins.extensions.gson
import challenge.android.gradleplugins.extensions.kotlinLibrary
import challenge.android.gradleplugins.extensions.shimmer
import challenge.android.gradleplugins.extensions.timber

plugins {
    id("challenge.module.android.framework")
}
repositories {
    google()
}
dependencies {
    androidXCore()
    coroutines()
    timber()
    kotlinLibrary()
    gson()
    androidMaterialDesign()
    espressoIdlingResource()
    // why -> we have common extension functions for shimmer layout. When `common` is split into common-ui, this can be removed
    shimmer()
}
