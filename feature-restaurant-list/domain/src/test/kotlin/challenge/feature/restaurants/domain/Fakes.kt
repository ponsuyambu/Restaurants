package challenge.feature.restaurants.domain

object Fakes {
    val RESTAURANT_1 = Restaurant(
        "1", "Pizza to go", RestaurantStatus.OPEN, 4f,
        20f, 45f, 500, 3f,
        21, 0, 33
    )

    val RESTAURANT_2 = Restaurant(
        "2", "Delhi 6", RestaurantStatus.CLOSED, 3f,
        50f, 145f, 1500, 13f,
        12, 0, 22
    )

    val RESTAURANT_3 = Restaurant(
        "3", "Call for Pizza", RestaurantStatus.ORDER_AHEAD, 3f,
        50f, 15f, 700, 113f,
        23, 6, 20
    )

    val RESTAURANT_LIST = listOf(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)
}
