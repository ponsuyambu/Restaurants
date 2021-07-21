package challenge.android.feature.restaurants.presentation.uimodels

data class RestaurantUiModel(
    val id: String,
    val name: String,
    val status: String,
    val rating: Float,
    val matchScore: Float,
    val newScaleScore: Float,
    val distance: String,
    val popularityScore: Float,
    val minimumCost: String,
    val deliveryCost: String,
    val averagePrice: String,
)
