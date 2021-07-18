package challenge.android.feature.restaurants.presentation.uimodels

data class RestaurantUiModel(
    val name: String,
    val status: String,
    val rating: Float,
    val matchScore: Float,
    val newScaleScore: Float,
    val distance: Float,
    val popularityScore: Float,
    val minimumCost: Float,
    val deliveryCost: Float,
    val averagePrice: Float,
)
