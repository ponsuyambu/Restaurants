package challenge.feature.restaurants.data.mapper

import challenge.feature.restaurants.data.models.RawRestaurantList
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

fun RawRestaurantList.toRestaurants(): List<Restaurant> {
    val restaurants = mutableListOf<Restaurant>()
    this.restaurants.forEach {
        restaurants.add(
            Restaurant(
                it.id,
                it.name,
                it.status.toRestaurantStatus(),
                it.sortingValues.ratingAverage,
                it.sortingValues.bestMatch,
                it.sortingValues.newest,
                it.sortingValues.distance,
                it.sortingValues.popularity,
                it.sortingValues.minCost,
                it.sortingValues.deliveryCosts,
                it.sortingValues.averageProductPrice,
            )
        )
    }
    return restaurants
}

private fun String.toRestaurantStatus() = when (this) {
    "open" -> RestaurantStatus.OPEN
    "closed" -> RestaurantStatus.CLOSED
    "order ahead" -> RestaurantStatus.ORDER_AHEAD
    else -> RestaurantStatus.CLOSED
}
