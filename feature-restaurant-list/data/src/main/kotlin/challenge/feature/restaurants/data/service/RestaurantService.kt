package challenge.feature.restaurants.data.service


interface RestaurantService {
    suspend fun getRestaurants(): String
}
