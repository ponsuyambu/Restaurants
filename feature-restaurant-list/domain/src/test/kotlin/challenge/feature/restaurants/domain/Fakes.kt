package challenge.feature.restaurants.domain

object Fakes {
    val RESTAURANT_1 = Restaurant(
        "1", "Pizza to go", RestaurantStatus.OPEN, 4f,
        10f, 45f, 5, 13f,
        21, 0, 3
    )

    val RESTAURANT_2 = Restaurant(
        "2", "Delhi 6", RestaurantStatus.OPEN, 4f,
        10f, 145f, 15, 13f,
        12, 0, 2
    )

    val RESTAURANT_3 = Restaurant(
        "3", "Call for Pizza", RestaurantStatus.OPEN, 4f,
        20f, 15f, 5, 113f,
        23, 6, 20
    )

    val RESTAURANT_LIST = listOf(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)
}
