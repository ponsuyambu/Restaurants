package challenge.feature.restaurants.domain

import challenge.feature.restaurants.domain.SortType.AVERAGE_PRICE
import challenge.feature.restaurants.domain.SortType.BEST_MATCH
import challenge.feature.restaurants.domain.SortType.DELIVERY_COST
import challenge.feature.restaurants.domain.SortType.DISTANCE
import challenge.feature.restaurants.domain.SortType.MINIMUM_COST
import challenge.feature.restaurants.domain.SortType.NEWEST
import challenge.feature.restaurants.domain.SortType.POPULARITY
import challenge.feature.restaurants.domain.SortType.RATING
import challenge.feature.restaurants.domain.SortType.STATUS
import javax.inject.Inject


class RestaurantsSorter @Inject constructor() {
    private fun sortRestaurantsBy(
        compareBy: ((Restaurant) -> Comparable<*>?),
        restaurants: List<Restaurant>,
        descending: Boolean = false
    ): List<Restaurant> {
        val comparator: ((Restaurant) -> Comparable<*>?) -> Comparator<Restaurant> =
            if (descending) ::compareByDescending else ::compareBy
        return restaurants.sortedWith(comparator(compareBy).thenBy { it.name })
    }

    /**
     * Sorts the restaurant with the given sort type. If the restaurants have the same sort value,
     * it considers the restaurant name as a secondary sort preference
     */
    operator fun invoke(restaurants: List<Restaurant>, sortType: SortType): List<Restaurant> {
        return when (sortType) {
            STATUS -> sortRestaurantsBy({ it.status }, restaurants)
            BEST_MATCH -> sortRestaurantsBy({ it.matchScore }, restaurants, true)
            NEWEST -> sortRestaurantsBy({ it.newScaleScore }, restaurants, true)
            RATING -> sortRestaurantsBy({ it.rating }, restaurants, true)
            DISTANCE -> sortRestaurantsBy({ it.distance }, restaurants)
            POPULARITY -> sortRestaurantsBy({ it.popularityScore }, restaurants, true)
            AVERAGE_PRICE -> sortRestaurantsBy({ it.averagePrice }, restaurants)
            DELIVERY_COST -> sortRestaurantsBy({ it.deliveryCost }, restaurants)
            MINIMUM_COST -> sortRestaurantsBy({ it.minimumCost }, restaurants)
        }
    }
}
