package challenge.android.feature.restaurants.presentation.uimodels

data class RestaurantUiModel(
    val id: String,
    val name: String,
    val status: String,
    val rating: Float,
    val matchScore: Float,
    val newScaleScore: Float,
    val distance: Int,
    val popularityScore: Float,
    val minimumCost: Int,
    val deliveryCost: Int,
    val averagePrice: Int,
)
