package challenge.feature.restaurants.domain

data class Restaurant(
    val id: String,
    val name: String,
    val status: RestaurantStatus,
    val rating: Float,
    val matchScore: Float,
    val newScaleScore: Float,
    val distance: Int,
    val popularityScore: Float,
    val minimumCost: Int,
    val deliveryCost: Int,
    val averagePrice: Int,
)

enum class RestaurantStatus {
    OPEN, ORDER_AHEAD, CLOSED
}
