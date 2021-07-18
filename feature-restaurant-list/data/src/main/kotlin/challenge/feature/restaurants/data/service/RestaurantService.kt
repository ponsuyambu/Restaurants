package challenge.feature.restaurants.data.service

import challenge.feature.restaurants.domain.Restaurant

interface RestaurantService {
    suspend fun getRestaurants(): List<Restaurant>
}
