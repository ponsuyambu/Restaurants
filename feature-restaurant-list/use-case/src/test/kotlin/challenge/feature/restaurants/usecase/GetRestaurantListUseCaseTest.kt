package challenge.feature.restaurants.usecase

import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.data.RestaurantRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

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
    fun `should call repository when use case is invoked`() = testCoroutineRule.runBlockingTest {
        coEvery { repository.getRestaurants() } returns listOf(RESTAURANT_1, RESTAURANT_2)

        val restaurants = getRestaurantListUseCase()

        coVerify { repository.getRestaurants() }
        assertEquals(listOf(RESTAURANT_1, RESTAURANT_2), restaurants)
    }
}
