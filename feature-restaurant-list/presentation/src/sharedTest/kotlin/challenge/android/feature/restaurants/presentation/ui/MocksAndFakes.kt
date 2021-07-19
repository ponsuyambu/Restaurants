package challenge.android.feature.restaurants.presentation.ui

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

val RESTAURANT_UI_MODEL_1 =
    RestaurantUiModel("1", "Restaurant 1", "Open", 4.5f, 10f, 20f, 12, 10f, 55, 33, 35)
val RESTAURANT_UI_MODEL_2 =
    RestaurantUiModel("2", "Restaurant 2", "Closed", 4f, 16f, 4f, 65, 43f, 76, 23, 34)

val RESTAURANT_1 = Restaurant(
    "1",
    "Restaurant 1",
    RestaurantStatus.OPEN,
    4f,
    10f,
    145f,
    15,
    13f,
    12,
    10,
    8
)

val RESTAURANT_2 = Restaurant(
    "2",
    "Restaurant 2",
    RestaurantStatus.ORDER_AHEAD,
    3f,
    10f,
    145f,
    15,
    13f,
    5,
    1,
    23
)
