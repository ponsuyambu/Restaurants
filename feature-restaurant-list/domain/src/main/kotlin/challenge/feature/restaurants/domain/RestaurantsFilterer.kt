package challenge.feature.restaurants.domain

import java.util.Locale
import javax.inject.Inject

class RestaurantsFilterer @Inject constructor() {
    operator fun invoke(restaurants: List<Restaurant>, name: String): List<Restaurant> {
        if (name.isBlank()) return restaurants
        val nameFilter = name.lowercase(Locale.getDefault())
        return restaurants.filter {
            it.name.lowercase(Locale.getDefault()).contains(nameFilter)
        }
    }
}
