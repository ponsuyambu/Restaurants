package challenge.feature.restaurants.data

import challenge.feature.restaurants.data.service.RestaurantService
import challenge.feature.restaurants.domain.Restaurant
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantService: RestaurantService) {
    suspend fun getRestaurants(): List<Restaurant> = restaurantService.getRestaurants()
}
