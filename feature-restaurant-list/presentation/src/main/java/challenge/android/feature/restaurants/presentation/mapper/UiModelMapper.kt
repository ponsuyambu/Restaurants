package challenge.android.feature.restaurants.presentation.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.VisibleForTesting
import challenge.android.feature.restaurants.presentation.R
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import challenge.feature.restaurants.domain.SortType

@VisibleForTesting
internal const val CURRENCY_SYMBOL = "€"

@VisibleForTesting
internal const val DISTANCE_UNIT = "m"

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
        "$distance$DISTANCE_UNIT",
        popularityScore,
        "$CURRENCY_SYMBOL $minimumCost",
        "$CURRENCY_SYMBOL $deliveryCost",
        "$CURRENCY_SYMBOL $averagePrice"
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

@DrawableRes
fun SortType.displayIcon(): Int {
    return when (this) {
        SortType.STATUS -> R.drawable.ic_info
        SortType.BEST_MATCH -> R.drawable.ic_match
        SortType.NEWEST -> R.drawable.ic_new_scale
        SortType.RATING -> R.drawable.ic_star_rate
        SortType.DISTANCE -> R.drawable.ic_distance
        SortType.POPULARITY -> R.drawable.ic_popularity
        SortType.AVERAGE_PRICE -> R.drawable.ic_price
        SortType.DELIVERY_COST -> R.drawable.ic_bike
        SortType.MINIMUM_COST -> R.drawable.ic_basket
    }
}
