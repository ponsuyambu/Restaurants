package challenge.android.feature.restaurants.presentation.mapper

import challenge.android.feature.restaurants.presentation.Fakes
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.android.testutils.BaseTest
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import org.junit.Assert.assertEquals
import org.junit.Test

class UiModelMapperTest : BaseTest() {
    @Test
    fun `should convert Restaurant to RestaurantUiModel`() {
        val restaurantUiModel = Fakes.RESTAURANT_1.toUiModel()

        assertRestaurantWithUiModel(Fakes.RESTAURANT_1, restaurantUiModel)
    }

    @Test
    fun `should convert RestaurantList to RestaurantUiModelList`() {
        val restaurantUiModelList = listOf(Fakes.RESTAURANT_1, Fakes.RESTAURANT_2).toUiModel()

        assertRestaurantWithUiModel(Fakes.RESTAURANT_1, restaurantUiModelList[0])
        assertRestaurantWithUiModel(Fakes.RESTAURANT_2, restaurantUiModelList[1])
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
