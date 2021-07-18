import challenge.android.gradleplugins.extensions.appModules
import challenge.android.gradleplugins.extensions.retrofit
import challenge.android.gradleplugins.extensions.timber

plugins {
    id("challenge.module.android.framework")
}
dependencies {
    retrofit()
    timber()
    with(appModules()) {
        featureRestaurantList.data()
    }
}
