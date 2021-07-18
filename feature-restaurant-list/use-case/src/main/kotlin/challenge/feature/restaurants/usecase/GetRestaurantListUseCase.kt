package challenge.feature.restaurants.usecase

import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor() {
    suspend operator fun invoke(): List<Restaurant> {
        delay(3000)
        return mutableListOf<Restaurant>().apply {
            add(
                Restaurant(
                    "Restaurant 1",
                    RestaurantStatus.OPEN,
                    4f,
                    10f,
                    145f,
                    15f,
                    13f,
                    12f,
                    10f,
                    8f
                )
            )
            add(
                Restaurant(
                    "Restaurant 2",
                    RestaurantStatus.ORDER_AHEAD,
                    3f,
                    10f,
                    145f,
                    15f,
                    13f,
                    12f,
                    10f,
                    8f
                )
            )
        }
    }
}
