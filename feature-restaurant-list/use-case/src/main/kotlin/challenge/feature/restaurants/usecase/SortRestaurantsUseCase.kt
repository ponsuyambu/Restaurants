package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantsSorter
import challenge.feature.restaurants.domain.SortType
import javax.inject.Inject

class SortRestaurantsUseCase @Inject constructor(private val restaurantsSorter: RestaurantsSorter) {

    companion object {
        val DEFAULT_SORT_TYPE = SortType.STATUS
    }

    operator fun invoke(
        restaurants: List<Restaurant>,
        sortType: SortType? = DEFAULT_SORT_TYPE
    ): List<Restaurant> =
        restaurantsSorter(restaurants, sortType ?: DEFAULT_SORT_TYPE)
}
