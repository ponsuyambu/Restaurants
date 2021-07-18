package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.data.RestaurantRepository
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(private val repository: RestaurantRepository) {
    suspend operator fun invoke() = repository.getRestaurants()
}
