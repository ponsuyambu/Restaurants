package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RestaurantsViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getRestaurantListUseCase: GetRestaurantListUseCase

    @RelaxedMockK
    private lateinit var showProgressObserver: Observer<Boolean>

    @RelaxedMockK
    private lateinit var errorObserver: Observer<String>

    @InjectMockKs
    private lateinit var viewModel: RestaurantsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel.showProgress().observeForever(showProgressObserver)
        viewModel.error().observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        viewModel.showProgress().removeObserver(showProgressObserver)
        viewModel.error().removeObserver(errorObserver)
    }

    @Test
    fun `should handle progress bar when restaurant list requested`() =
        testCoroutineRule.runBlockingTest {
            coEvery { getRestaurantListUseCase.invoke() } returns mutableListOf()

            viewModel.requestRestaurants()

            coVerifyOrder {
                showProgressObserver.onChanged(true)
                getRestaurantListUseCase.invoke()
                showProgressObserver.onChanged(false)
            }
        }
}
