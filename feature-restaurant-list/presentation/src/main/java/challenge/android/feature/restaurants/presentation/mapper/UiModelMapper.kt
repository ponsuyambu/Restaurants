package challenge.android.feature.restaurants.presentation.mapper

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus

fun List<Restaurant>.toUiModel() = mutableListOf<RestaurantUiModel>().apply {
    this@toUiModel.forEach {
        add(it.toUiModel())
    }
}

fun Restaurant.toUiModel(): RestaurantUiModel {
    val status = when (status) {
        RestaurantStatus.OPEN -> "Open"
        RestaurantStatus.ORDER_AHEAD -> "Order Ahead"
        RestaurantStatus.CLOSED -> "Closed"
    }
    return RestaurantUiModel(
        name,
        status,
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
