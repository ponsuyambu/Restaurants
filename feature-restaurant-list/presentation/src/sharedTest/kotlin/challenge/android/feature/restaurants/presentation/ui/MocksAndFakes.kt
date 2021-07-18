package challenge.android.feature.restaurants.presentation.ui

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

val RESTAURANT_UI_MODEL_1 =
    RestaurantUiModel("Restaurant 1", "Open", 4.5f, 10f, 20f, 12f, 10f, 55f, 33f, 35f)
val RESTAURANT_UI_MODEL_2 =
    RestaurantUiModel("Restaurant 2", "Closed", 4f, 16f, 4f, 65f, 43f, 76f, 23f, 34f)

val RESTAURANT_1 = Restaurant(
    "Restaurant 1",
    RestaurantStatus.OPEN,
    4f,
    10f,
    145f,
    15f,
    13f,
    12f,
    10f,
    8f
)

val RESTAURANT_2 = Restaurant(
    "Restaurant 2",
    RestaurantStatus.ORDER_AHEAD,
    3f,
    10f,
    145f,
    15f,
    13f,
    5f,
    1f,
    23f
)
