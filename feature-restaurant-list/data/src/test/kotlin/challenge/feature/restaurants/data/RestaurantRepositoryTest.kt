package challenge.feature.restaurants.data

import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.data.service.RestaurantService
import challenge.feature.restaurants.domain.Restaurant
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RestaurantRepositoryTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var restaurantService: RestaurantService

    @InjectMockKs
    private lateinit var repository: RestaurantRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should call service when getRestaurants is invoked`() = testCoroutineRule.runBlockingTest {
        val restaurantList = mockk<List<Restaurant>>()
        coEvery { repository.getRestaurants() } returns restaurantList

        val restaurants = restaurantService.getRestaurants()

        coVerify { repository.getRestaurants() }
        Assert.assertEquals(restaurantList, restaurants)
    }
}