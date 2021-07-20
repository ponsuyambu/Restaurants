package challenge.feature.restaurants.domain

import challenge.android.testutils.BaseTest
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantsFiltererTest : BaseTest() {

    @InjectMockKs
    private lateinit var restaurantsFilterer: RestaurantsFilterer

    @Test
    fun `should filter the restaurant list when name value is not empty`() {
        val result = restaurantsFilterer(Fakes.RESTAURANT_LIST, "Pizza")

        assertEquals(listOf(Fakes.RESTAURANT_1, Fakes.RESTAURANT_3), result)
    }

    @Test
    fun `filter should be case insensitive`() {
        assertEquals(
            listOf(Fakes.RESTAURANT_2),
            restaurantsFilterer(Fakes.RESTAURANT_LIST, "Delhi")
        )

        assertEquals(
            listOf(Fakes.RESTAURANT_2),
            restaurantsFilterer(Fakes.RESTAURANT_LIST, "dElHi")
        )
    }

    @Test
    fun `should return the restaurant list when name value is empty`() {
        val result = restaurantsFilterer(Fakes.RESTAURANT_LIST, "")

        assertEquals(Fakes.RESTAURANT_LIST, result)
    }
}
