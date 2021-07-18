package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

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
