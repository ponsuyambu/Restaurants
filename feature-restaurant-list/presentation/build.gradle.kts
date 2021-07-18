import challenge.android.gradleplugins.extensions.appModules

plugins {
    id("challenge.module.android.presentation")
}
dependencies {
    with(appModules()) {
        featureRestaurantList.useCase()
        featureRestaurantList.domain()
    }
}
