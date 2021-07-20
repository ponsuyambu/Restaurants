package challenge.feature.restaurants.data

import challenge.android.testutils.BaseTest
import challenge.feature.restaurants.data.mapper.toDomainModel
import challenge.feature.restaurants.data.mapper.toRestaurantStatus
import challenge.feature.restaurants.data.models.RawRestaurant
import challenge.feature.restaurants.data.models.RawRestaurantList
import challenge.feature.restaurants.data.models.SortingValues
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantStatus
import challenge.feature.restaurants.domain.RestaurantStatus.OPEN
import org.junit.Assert.assertEquals
import org.junit.Test

class DomainDataMapperTest : BaseTest() {

    @Test
    fun `should convert raw string to restaurant status`() {
        assertEquals(OPEN, "open".toRestaurantStatus())
        assertEquals(RestaurantStatus.CLOSED, "closed".toRestaurantStatus())
        assertEquals(RestaurantStatus.ORDER_AHEAD, "order ahead".toRestaurantStatus())
    }

    @Test
    fun `should convert as 'CLOSED' when invalid restaurant status is given`() {
        assertEquals(RestaurantStatus.CLOSED, "invalid status".toRestaurantStatus())
    }

    @Test
    fun `should convert RawRestaurantList to RestaurantList when toRestaurants is invoked`() {
        val rawRestaurants = RawRestaurantList(
            listOf(
                RawRestaurant(
                    "1", "first", "open",
                    SortingValues(
                        1f, 2f, 3f,
                        4, 5f, 6, 7, 8
                    )
                )
            )
        )
        val expectedOutput = listOf(
            Restaurant(
                "1", "first", OPEN, 3f, 1f, 2f,
                4, 5f, 8, 7, 6
            )
        )

        val result = rawRestaurants.toDomainModel()

        assertEquals(expectedOutput, result)
    }
}
