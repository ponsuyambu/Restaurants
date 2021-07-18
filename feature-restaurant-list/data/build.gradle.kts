import challenge.android.gradleplugins.extensions.appModules
import challenge.android.gradleplugins.extensions.gson

plugins {
    id("challenge.module.data")
}

dependencies {
    with(appModules()) {
        featureRestaurantList.domain()
    }
    gson()
}
