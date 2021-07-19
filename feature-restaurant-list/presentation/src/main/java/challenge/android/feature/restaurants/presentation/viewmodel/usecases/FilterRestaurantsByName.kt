package challenge.android.feature.restaurants.presentation.viewmodel.usecases

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import java.util.Locale
import javax.inject.Inject

class FilterRestaurantsByName @Inject constructor() {
    operator fun invoke(
        restaurants: List<RestaurantUiModel>,
        filterValue: String
    ): List<RestaurantUiModel> {
        if (filterValue.isBlank()) return restaurants
        val filteredRestaurants = mutableListOf<RestaurantUiModel>()
        filterValue.lowercase(Locale.getDefault()).also { nameFilter ->
            restaurants.forEach {
                if (it.name.lowercase(Locale.getDefault()).contains(nameFilter)) {
                    filteredRestaurants.add(it)
                }
            }
        }
        return filteredRestaurants
    }
}
