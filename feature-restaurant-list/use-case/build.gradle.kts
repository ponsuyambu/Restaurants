import challenge.android.gradleplugins.extensions.appModules

plugins {
    id("challenge.module.usecase")
}
dependencies {
    with(appModules()) {
        featureRestaurantList.domain()
        featureRestaurantList.data()
    }
}
