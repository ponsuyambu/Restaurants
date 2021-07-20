package challenge.feature.restaurants.domain

import java.util.Locale
import javax.inject.Inject

class RestaurantsFilterer @Inject constructor() {
    operator fun invoke(restaurants: List<Restaurant>, filterValue: String): List<Restaurant> {
        if (filterValue.isBlank()) return restaurants
        val nameFilter = filterValue.lowercase(Locale.getDefault())
        return restaurants.filter {
            it.name.lowercase(Locale.getDefault()).contains(nameFilter)
        }
    }
}
