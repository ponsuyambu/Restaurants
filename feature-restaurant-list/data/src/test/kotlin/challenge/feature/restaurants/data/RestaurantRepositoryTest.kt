package challenge.feature.restaurants.data

import challenge.android.testutils.BaseTest
import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.data.models.RawResponseResult
import challenge.feature.restaurants.data.service.RestaurantService
import challenge.feature.restaurants.domain.Restaurant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test

class RestaurantRepositoryTest : BaseTest() {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var restaurantService: RestaurantService

    @InjectMockKs
    private lateinit var repository: RestaurantRepository

    @Test
    fun `should call service when getRestaurants is invoked`() = testCoroutineRule.runBlockingTest {
        coEvery { restaurantService.getRestaurants() } returns RAW_RESPONSE

        repository.getRestaurants()

        coVerify { restaurantService.getRestaurants() }
    }

    @Test
    fun `should return success response when service returns success response`() =
        testCoroutineRule.runBlockingTest {
            coEvery { restaurantService.getRestaurants() } returns RAW_RESPONSE

            when (val response: RawResponseResult<List<Restaurant>> = repository.getRestaurants()) {
                is RawResponseResult.Error -> fail("Should be a success response-> ${response.exception}")
                is RawResponseResult.Success -> {
                    assertEquals(RAW_RESPONSE_RESTAURANTS_SIZE, response.data.size)
                    assertEquals(RAW_RESPONSE_RESTAURANT_1_NAME, response.data[0].name)
                    assertEquals(RAW_RESPONSE_RESTAURANT_1_ID, response.data[0].id)
                    assertEquals(RAW_RESPONSE_RESTAURANT_2_NAME, response.data[1].name)
                    assertEquals(RAW_RESPONSE_RESTAURANT_2_ID, response.data[1].id)
                }
            }
        }

    @Test
    fun `should return error response when service returns error or invalid data`() =
        testCoroutineRule.runBlockingTest {
            coEvery { restaurantService.getRestaurants() } returns "Invalid data"

            assertTrue(repository.getRestaurants() is RawResponseResult.Error)
        }
}
