package challenge.feature.restaurants.data.models

data class RawRestaurantList(
    val restaurants: List<RawRestaurant>
)

data class RawRestaurant(
    val id: String,
    val name: String,
    val status: String,
    val sortingValues: SortingValues
)

data class SortingValues(
    val bestMatch: Float,
    val newest: Float,
    val ratingAverage: Float,
    val distance: Int,
    val popularity: Float,
    val averageProductPrice: Int,
    val deliveryCosts: Int,
    val minCost: Int
)
