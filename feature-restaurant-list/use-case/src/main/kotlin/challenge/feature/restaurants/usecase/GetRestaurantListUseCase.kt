package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.data.RestaurantRepository
import challenge.feature.restaurants.data.models.RawResponseResult
import challenge.feature.restaurants.domain.Restaurant
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(private val repository: RestaurantRepository) {
    suspend operator fun invoke(): List<Restaurant> {
        return when (val result = repository.getRestaurants()) {
            // Handle any specific errors here -> map business specific error code here
            // For this challenge, no specific error code handled. Simply errors are throws to the above layer
            is RawResponseResult.Error -> throw result.exception
            is RawResponseResult.Success -> result.data
        }
    }
}
