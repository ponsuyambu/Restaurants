package challenge.feature.restaurants.usecase

import challenge.android.testutils.BaseTest
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.domain.RestaurantsFilterer
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Test

class FilterRestaurantsUseCaseTest : BaseTest() {

    @RelaxedMockK
    private lateinit var restaurantsFilterer: RestaurantsFilterer

    @InjectMockKs
    private lateinit var filterRestaurantsUseCase: FilterRestaurantsUseCase

    @Test
    fun `should call restaurants filterer when the case case is invoked`() {
        val mockRestaurants = mockk<List<Restaurant>>()
        val nameFilter = "some filter value"
        val restaurantsSlot = slot<List<Restaurant>>()
        val nameFilterSlot = slot<String>()

        filterRestaurantsUseCase(mockRestaurants, nameFilter)

        verify { restaurantsFilterer.invoke(capture(restaurantsSlot), capture(nameFilterSlot)) }
        // Should pass the same reference, alert: '===' not '=='
        assertTrue(mockRestaurants === restaurantsSlot.captured)
        assertTrue(nameFilter === nameFilterSlot.captured)
    }
}
