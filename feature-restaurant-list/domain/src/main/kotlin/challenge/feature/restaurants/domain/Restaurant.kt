package challenge.feature.restaurants.domain

data class Restaurant(
    val name: String,
    val status: RestaurantStatus,
    val rating: Float,
    val matchScore: Float,
    val newScaleScore: Float,
    val distance: Float,
    val popularityScore: Float,
    val minimumCost: Float,
    val deliveryCost: Float,
    val averagePrice: Float,
)

enum class RestaurantStatus {
    OPEN, ORDER_AHEAD, CLOSED
}
