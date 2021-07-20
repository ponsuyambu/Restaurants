package challenge.android.feature.restaurants.presentation.mapper

import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import challenge.feature.restaurants.domain.SortType

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
        id,
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

fun SortType.displayName(): String {
    return when (this) {
        SortType.STATUS -> "Status"
        SortType.BEST_MATCH -> "Best Match"
        SortType.NEWEST -> "Newest"
        SortType.RATING -> "Rating"
        SortType.DISTANCE -> "Distance"
        SortType.POPULARITY -> "Popularity"
        SortType.AVERAGE_PRICE -> "Average Price"
        SortType.DELIVERY_COST -> "Delivery Cost"
        SortType.MINIMUM_COST -> "Minimum Cost"
    }
}
