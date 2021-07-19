package challenge.android.feature.restaurants.presentation.mapper

import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_1
import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_2
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UiModelMapperTest {
    @Test
    fun `should convert Restaurant to RestaurantUiModel`() {
        val restaurantUiModel = RESTAURANT_1.toUiModel()

        assertRestaurantWithUiModel(RESTAURANT_1, restaurantUiModel)
    }

    @Test
    fun `should convert RestaurantList to RestaurantUiModelList`() {
        val restaurantUiModelList = listOf(RESTAURANT_1, RESTAURANT_2).toUiModel()

        assertRestaurantWithUiModel(RESTAURANT_1, restaurantUiModelList[0])
        assertRestaurantWithUiModel(RESTAURANT_2, restaurantUiModelList[1])
    }

    @Test
    fun `should show the display name for the status`() {
        assertEquals("Open", RestaurantStatus.OPEN.displayName())
        assertEquals("Closed", RestaurantStatus.CLOSED.displayName())
        assertEquals("Order Ahead", RestaurantStatus.ORDER_AHEAD.displayName())
    }

    private fun assertRestaurantWithUiModel(restaurant: Restaurant, uiModel: RestaurantUiModel) {
        assertEquals(restaurant.id, uiModel.id)
        assertEquals(restaurant.name, uiModel.name)
        assertEquals(restaurant.status.displayName(), uiModel.status)
        assertEquals(restaurant.averagePrice, uiModel.averagePrice)
        assertEquals(restaurant.deliveryCost, uiModel.deliveryCost)
        assertEquals(restaurant.distance, uiModel.distance)
        assertEquals(restaurant.matchScore, uiModel.matchScore)
        assertEquals(restaurant.minimumCost, uiModel.minimumCost)
        assertEquals(restaurant.newScaleScore, uiModel.newScaleScore)
        assertEquals(restaurant.averagePrice, uiModel.averagePrice)
        assertEquals(restaurant.popularityScore, uiModel.popularityScore)
    }
}
