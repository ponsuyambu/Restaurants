package challenge.android.feature.restaurants.presentation.mapper

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

fun List<Restaurant>.toUiModel(): List<RestaurantUiModel> =
    mutableListOf<RestaurantUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }

fun RestaurantStatus.displayName() = when (this) {
    RestaurantStatus.OPEN -> "Open"
    RestaurantStatus.ORDER_AHEAD -> "Order Ahead"
    RestaurantStatus.CLOSED -> "Closed"
}

fun Restaurant.toUiModel(): RestaurantUiModel {
    return RestaurantUiModel(
        name,
        status.displayName(),
        rating,
        matchScore,
        newScaleScore,
        distance,
        popularityScore,
        minimumCost,
        deliveryCost,
        averagePrice
    )
}
