package challenge.feature.restaurants.usecase

import challenge.android.testutils.BaseTest
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantsSorter
import challenge.feature.restaurants.domain.SortType
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SortRestaurantsUseCaseTest : BaseTest() {

    @RelaxedMockK
    private lateinit var restaurantsSorter: RestaurantsSorter

    @InjectMockKs
    private lateinit var sortRestaurantsUseCase: SortRestaurantsUseCase

    @Test
    fun `should call restaurant sorter when use case is invoked`() {
        val mockRestaurants = mockk<List<Restaurant>>()
        val sortType = mockk<SortType>()
        val restaurantsSlot = slot<List<Restaurant>>()
        val sortTypeSlot = slot<SortType>()

        sortRestaurantsUseCase(mockRestaurants, sortType)

        verify { restaurantsSorter.invoke(capture(restaurantsSlot), capture(sortTypeSlot)) }
        // Should pass the same reference, alert: '===' not '=='
        assertTrue(mockRestaurants === restaurantsSlot.captured)
        assertTrue(sortType === sortTypeSlot.captured)
    }

    @Test
    fun `should use 'STATUS' as default sort type`() {
        val mockRestaurants = mockk<List<Restaurant>>()
        val restaurantsSlot = slot<List<Restaurant>>()
        val sortTypeSlot = slot<SortType>()

        sortRestaurantsUseCase(mockRestaurants)

        verify { restaurantsSorter.invoke(capture(restaurantsSlot), capture(sortTypeSlot)) }
        assertEquals(SortType.STATUS, sortTypeSlot.captured)
    }

    @Test
    fun `should use 'STATUS' as sort type when 'null' is passed as sort type`() {
        val mockRestaurants = mockk<List<Restaurant>>()
        val restaurantsSlot = slot<List<Restaurant>>()
        val sortTypeSlot = slot<SortType>()

        sortRestaurantsUseCase(mockRestaurants, null)

        verify { restaurantsSorter.invoke(capture(restaurantsSlot), capture(sortTypeSlot)) }
        assertEquals(SortType.STATUS, sortTypeSlot.captured)
    }
}
