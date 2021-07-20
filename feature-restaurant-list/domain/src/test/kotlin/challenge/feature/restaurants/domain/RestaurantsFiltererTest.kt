package challenge.feature.restaurants.domain

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantsFiltererTest {

    @InjectMockKs
    private lateinit var restaurantsFilterer: RestaurantsFilterer

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

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
