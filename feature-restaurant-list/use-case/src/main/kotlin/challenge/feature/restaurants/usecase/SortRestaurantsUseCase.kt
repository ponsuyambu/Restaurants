package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantsSorter
import challenge.feature.restaurants.domain.SortType
import javax.inject.Inject

class SortRestaurantsUseCase @Inject constructor(private val restaurantsSorter: RestaurantsSorter) {
    private val defaultSortType = SortType.STATUS

    operator fun invoke(
        restaurants: List<Restaurant>,
        sortType: SortType? = defaultSortType
    ): List<Restaurant> =
        restaurantsSorter(restaurants, sortType ?: defaultSortType)
}
