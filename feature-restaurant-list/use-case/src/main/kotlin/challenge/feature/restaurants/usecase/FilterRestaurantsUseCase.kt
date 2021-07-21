package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantsFilterer
import javax.inject.Inject

class FilterRestaurantsUseCase @Inject constructor(private val restaurantsFilterer: RestaurantsFilterer) {
    operator fun invoke(restaurants: List<Restaurant>, filterValue: String): List<Restaurant> =
        restaurantsFilterer(restaurants, filterValue)
}
