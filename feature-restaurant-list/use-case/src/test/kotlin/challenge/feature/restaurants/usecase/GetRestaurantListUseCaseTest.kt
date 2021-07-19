package challenge.feature.restaurants.usecase

import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.data.RestaurantRepository
import challenge.feature.restaurants.data.models.RawResponseResult
import challenge.feature.restaurants.domain.Restaurant
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.ConnectException

@RunWith(JUnit4::class)
class GetRestaurantListUseCaseTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: RestaurantRepository

    @InjectMockKs
    private lateinit var getRestaurantListUseCase: GetRestaurantListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should throw error when repository returns failure`() {
        coEvery { repository.getRestaurants() } returns RawResponseResult.Error(ConnectException())

        val exception = assertThrows(Exception::class.java) {
            testCoroutineRule.runBlockingTest {
                getRestaurantListUseCase()
            }
        }

        assertTrue(exception is ConnectException)
    }

    @Test
    fun `should return restaurants list when repository returns success response`() =
        testCoroutineRule.runBlockingTest {
            val mockRestaurants = mockk<List<Restaurant>>()
            coEvery { repository.getRestaurants() } returns RawResponseResult.Success(
                mockRestaurants
            )

            val restaurants = getRestaurantListUseCase()

            assertEquals(mockRestaurants, restaurants)
        }
}
