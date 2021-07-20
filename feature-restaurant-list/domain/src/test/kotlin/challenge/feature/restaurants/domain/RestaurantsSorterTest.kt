package challenge.feature.restaurants.domain

import challenge.android.testutils.BaseTest
import challenge.feature.restaurants.domain.Fakes.RESTAURANT_1
import challenge.feature.restaurants.domain.Fakes.RESTAURANT_2
import challenge.feature.restaurants.domain.Fakes.RESTAURANT_3
import challenge.feature.restaurants.domain.Fakes.RESTAURANT_LIST
import challenge.feature.restaurants.domain.SortType.AVERAGE_PRICE
import challenge.feature.restaurants.domain.SortType.BEST_MATCH
import challenge.feature.restaurants.domain.SortType.DELIVERY_COST
import challenge.feature.restaurants.domain.SortType.DISTANCE
import challenge.feature.restaurants.domain.SortType.MINIMUM_COST
import challenge.feature.restaurants.domain.SortType.NEWEST
import challenge.feature.restaurants.domain.SortType.POPULARITY
import challenge.feature.restaurants.domain.SortType.RATING
import challenge.feature.restaurants.domain.SortType.STATUS
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters


@RunWith(Parameterized::class)
class RestaurantsSorterTest(
    private val sortType: SortType,
    private val afterSortRestaurants: List<Restaurant>,
) : BaseTest() {

    @InjectMockKs
    private lateinit var restaurantsSorter: RestaurantsSorter

    companion object {
        @JvmStatic
        @Parameters(name = "Sort by ({0}) => {1}")
        fun data() = listOf(
            arrayOf(STATUS, listOf(RESTAURANT_1, RESTAURANT_3, RESTAURANT_2)),
            arrayOf(BEST_MATCH, listOf(RESTAURANT_3, RESTAURANT_2, RESTAURANT_1)),
            arrayOf(NEWEST, listOf(RESTAURANT_2, RESTAURANT_1, RESTAURANT_3)),
            arrayOf(
                RATING,
                listOf(RESTAURANT_1, RESTAURANT_3, RESTAURANT_2)
            ), // Here two restaurants have the same rating
            arrayOf(DISTANCE, listOf(RESTAURANT_1, RESTAURANT_3, RESTAURANT_2)),
            arrayOf(POPULARITY, listOf(RESTAURANT_3, RESTAURANT_2, RESTAURANT_1)),
            arrayOf(AVERAGE_PRICE, listOf(RESTAURANT_3, RESTAURANT_2, RESTAURANT_1)),
            arrayOf(DELIVERY_COST, listOf(RESTAURANT_2, RESTAURANT_1, RESTAURANT_3)),
            arrayOf(MINIMUM_COST, listOf(RESTAURANT_2, RESTAURANT_1, RESTAURANT_3)),
        )
    }

    @Test
    fun `should sort the restaurants for the given sort type`() {
        val sortedResult = restaurantsSorter(RESTAURANT_LIST, sortType)

        assertEquals(afterSortRestaurants, sortedResult)
    }
}