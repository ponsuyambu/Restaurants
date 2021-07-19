package challenge.feature.restaurants.data

import challenge.feature.restaurants.data.mapper.toRestaurants
import challenge.feature.restaurants.data.models.RawResponseResult
import challenge.feature.restaurants.data.models.RawRestaurantList
import challenge.feature.restaurants.data.service.RestaurantService
import challenge.feature.restaurants.domain.Restaurant
import com.google.gson.Gson
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantService: RestaurantService) {
    suspend fun getRestaurants(): RawResponseResult<List<Restaurant>> {
        return try {
            val response = restaurantService.getRestaurants()
            val rawRestaurants = Gson().fromJson(response, RawRestaurantList::class.java)
            // If error messages are wrapped inside success response json, unwrap it and create a new custom exception to send RawResponseResult.Error
            RawResponseResult.Success(rawRestaurants.toRestaurants())
        } catch (e: Exception) {
            RawResponseResult.Error(e)
        }
    }
}
