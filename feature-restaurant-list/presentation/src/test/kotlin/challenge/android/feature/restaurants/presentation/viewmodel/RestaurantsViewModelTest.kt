package challenge.android.feature.restaurants.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import challenge.android.feature.restaurants.presentation.mapper.toUiModel
import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_1
import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_2
import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_UI_MODEL_1
import challenge.android.feature.restaurants.presentation.ui.RESTAURANT_UI_MODEL_2
import challenge.android.feature.restaurants.presentation.uimodels.RestaurantUiModel
import challenge.android.testutils.TestCoroutineRule
import challenge.feature.restaurants.domain.Restaurant
import challenge.feature.restaurants.usecase.GetRestaurantListUseCase
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.clearStaticMockk
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

private const val AN_ERROR = "an error occurred"

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

    @RelaxedMockK
    private lateinit var showRestaurantsListObserver: Observer<Boolean>

    @InjectMockKs
    private lateinit var viewModel: RestaurantsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel.showProgress().observeForever(showProgressObserver)
        viewModel.error().observeForever(errorObserver)
        viewModel.showRestaurantsList().observeForever(showRestaurantsListObserver)
        coEvery { getRestaurantListUseCase.invoke() } returns listOf(RESTAURANT_1, RESTAURANT_2)
    }

    @After
    fun tearDown() {
        viewModel.showProgress().removeObserver(showProgressObserver)
        viewModel.error().removeObserver(errorObserver)
        viewModel.showRestaurantsList().removeObserver(showRestaurantsListObserver)
        clearAllMocks()
        clearStaticMockk()
    }

    @Test
    fun `should handle progress bar when restaurant list requested`() =
        testCoroutineRule.runBlockingTest {
            viewModel.makeInitialRestaurantsRequest()

            coVerifyOrder {
                showProgressObserver.onChanged(true)
                getRestaurantListUseCase.invoke()
                showProgressObserver.onChanged(false)
            }
        }

    @Test
    fun `should show error when error happened`() = testCoroutineRule.runBlockingTest {
        coEvery { getRestaurantListUseCase.invoke() } throws Exception(AN_ERROR)

        viewModel.makeInitialRestaurantsRequest()

        verify { errorObserver.onChanged(AN_ERROR) }
    }

    @Test
    fun `should update restaurant list when server returns data`() =
        testCoroutineRule.runBlockingTest {
            val restaurantList = mockk<List<Restaurant>>()
            coEvery { getRestaurantListUseCase.invoke() } returns restaurantList
            mockkStatic("challenge.android.feature.restaurants.presentation.mapper.UiModelMapperKt")
            val listOf: List<RestaurantUiModel> =
                listOf(RESTAURANT_UI_MODEL_1, RESTAURANT_UI_MODEL_2)
            every { restaurantList.toUiModel() } returns listOf

            viewModel.makeInitialRestaurantsRequest()

            val restaurants = viewModel.restaurants()
            coVerifyOrder {
                showProgressObserver.onChanged(true)
                getRestaurantListUseCase.invoke()
                showRestaurantsListObserver.onChanged(true)
                showProgressObserver.onChanged(false)
                assertEquals(2, restaurants.size)
                assertEquals(RESTAURANT_UI_MODEL_1, restaurants[0])
                assertEquals(RESTAURANT_UI_MODEL_2, restaurants[1])
            }
        }
}
