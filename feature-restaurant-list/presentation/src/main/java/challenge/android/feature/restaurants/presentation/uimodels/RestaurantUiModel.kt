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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RestaurantUiModel) return false

        if (name != other.name) return false
        if (status != other.status) return false
        if (rating != other.rating) return false
        if (matchScore != other.matchScore) return false
        if (newScaleScore != other.newScaleScore) return false
        if (distance != other.distance) return false
        if (popularityScore != other.popularityScore) return false
        if (minimumCost != other.minimumCost) return false
        if (deliveryCost != other.deliveryCost) return false
        if (averagePrice != other.averagePrice) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + matchScore.hashCode()
        result = 31 * result + newScaleScore.hashCode()
        result = 31 * result + distance.hashCode()
        result = 31 * result + popularityScore.hashCode()
        result = 31 * result + minimumCost.hashCode()
        result = 31 * result + deliveryCost.hashCode()
        result = 31 * result + averagePrice.hashCode()
        return result
    }
}
